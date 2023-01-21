package com.example.spacexlaunches.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.R
import com.example.spacexlaunches.adapter.LaunchAdapter
import com.example.spacexlaunches.databinding.FragmentMissionListBinding
import com.example.spacexlaunches.models.Mission.MissionModel
import com.example.spacexlaunches.room.MissionsListApplication
import com.example.spacexlaunches.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MissionListFragment : Fragment(), OnClickListener {

    lateinit var binding: FragmentMissionListBinding
    private lateinit var launchAdapter: LaunchAdapter
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMissionListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true); //Make sure you have this line of code.

        CoroutineScope(Dispatchers.IO).launch {
            if (MissionsListApplication.database.launchDao().getAll().isEmpty()) {
                viewModel.onlyShowPinned.postValue(false)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.missionList.value?.let {
            setUpRecyclerView(it)
        }

        viewModel.missionList.observe(viewLifecycleOwner, Observer {
            if(viewModel.missionList.value == null){
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
            else{
                setUpRecyclerView(it)
            }
        })

        viewModel.onlyShowPinned.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.pinnedButton.setBackgroundResource(R.drawable.fav_pin_blue);
                viewModel.loadPinnedList()
            } else
            {
                binding.pinnedButton.setBackgroundResource(R.drawable.fav_pin_white);
                viewModel.loadInitData()
            }
        })
        binding.searchTextInput.setText(viewModel.searchText.value.toString())

        viewModel.searchText.observe(viewLifecycleOwner, Observer {

            if(it.isNullOrEmpty()) {
                viewModel.missionList.value?.let { it1 -> setUpRecyclerView(it1) }
            } else
            {
                viewModel.searchMissionList.value!!.clear()
                for (i in viewModel.missionList.value!!) {

                    if(i.name!!.lowercase().contains(it.toString().lowercase()))
                    {
                        viewModel.searchMissionList.value!!.add(i)
                    }
                }
                viewModel.searchMissionList.value?.let { setUpRecyclerView(it) }
            }
        })

        binding.searchTextInput.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                viewModel.searchText.postValue(p0.toString())
            }
        })

        binding.pinnedButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                if (MissionsListApplication.database.launchDao().getAll().isEmpty()) {

                    if(viewModel.onlyShowPinned.value == true)
                    {
                        viewModel.onlyShowPinned.postValue(false)

                    } else
                    {
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, "No hay datos guardados", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else
                {
                    viewModel.onlyShowPinned.postValue(!viewModel.onlyShowPinned.value!!)
                }
            }
        }
    }

    private fun setUpRecyclerView(myData: List<MissionModel>) {

        launchAdapter = LaunchAdapter(myData, this, requireActivity())
        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = GridLayoutManager(context, 1)
            adapter = launchAdapter
        }
        binding.recyclerView.scrollToPosition(viewModel.scrollPos.value!!)
        binding.progressBar.visibility = View.GONE
        binding.searchTextInput.isEnabled = true
    }

    override fun onClick(mission: MissionModel) {
        binding.searchTextInput.setText("")
        viewModel.setLaunch(mission)
        if(mission.flightNumber!! > 0) {
            viewModel.scrollPos.postValue(mission.flightNumber!!-1)
        }
        findNavController().navigate(com.example.spacexlaunches.R.id.action_launchList_to_launchDetail)
    }
}
