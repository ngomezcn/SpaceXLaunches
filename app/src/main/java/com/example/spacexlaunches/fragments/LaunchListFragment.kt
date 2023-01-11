package com.example.spacexlaunches.fragments

import android.R.color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.R
import com.example.spacexlaunches.adapter.LaunchAdapter
import com.example.spacexlaunches.databinding.FragmentLaunchListBinding
import com.example.spacexlaunches.models.Launch.Failures
import com.example.spacexlaunches.models.Launch.LaunchModel
import com.example.spacexlaunches.room.LaunchesApplication
import com.example.spacexlaunches.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LaunchListFragment : Fragment(), OnClickListener {

    lateinit var binding: FragmentLaunchListBinding
    private lateinit var launchAdapter: LaunchAdapter
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLaunchListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true);//Make sure you have this line of code.
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.launchList.value?.let {

            setUpRecyclerView(it)
        }

        viewModel.launchList.observe(viewLifecycleOwner, Observer {
            if(viewModel.launchList.value == null){
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
            else{
                setUpRecyclerView(it)
            }
        })


        binding.searchTextInput.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                if(p0.isNullOrEmpty()) {
                    viewModel.launchList.value?.let { setUpRecyclerView(it) }
                } else
                {
                    viewModel.searchLaunchList.value!!.clear()
                    for (i in viewModel.launchList.value!!) {

                        if(i.name!!.lowercase().contains(p0.toString().lowercase()))
                        {
                            viewModel.searchLaunchList.value!!.add(i)
                        }
                    }
                    viewModel.searchLaunchList.value?.let { setUpRecyclerView(it) }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val normalDrawable = item.icon
        val wrapDrawable = DrawableCompat.wrap(normalDrawable)
        DrawableCompat.setTint(wrapDrawable, requireActivity().resources.getColor(R.color.favBlueColor))


        item.setIcon(wrapDrawable)

        println("FRAGMENT LaunchList")
        when(item.itemId) {
            R.id.menu_favBtn -> loadLocalDB()
        }

        return false
    }

    private fun loadLocalDB() {

        val models = mutableListOf<LaunchModel>()

        CoroutineScope(Dispatchers.IO).launch {

            val entities = LaunchesApplication.database.launchDao().getAll()

            for (i in entities) {
                val launch = LaunchModel()

                launch.name = i.name
                launch.dateUtc = i.dateUtc

                val failures = Failures()
                failures.reason = i.failures_reason
                launch.failures.add(failures)

                launch.details = i.details
                launch.success = i.success
                launch.flightNumber = i.flightNumber
                launch.links!!.patch!!.large = i.links_patch_large
                launch.links!!.patch!!.small = i.links_patch_small

                models.add(launch)
            }
        }

        setUpRecyclerView(models)
    }

    private fun setUpRecyclerView(myData: List<LaunchModel>) {
        launchAdapter = LaunchAdapter(myData, this)
        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = GridLayoutManager(context, 1)
            adapter = launchAdapter
        }
        binding.recyclerView.scrollToPosition(viewModel.scrollPos.value!!)
        binding.progressBar.visibility = View.GONE
        binding.searchTextInput.isEnabled = true
    }

    override fun onClick(launch: LaunchModel) {
        binding.searchTextInput.setText("")
        viewModel.setLaunch(launch)
        if(launch.flightNumber!! > 0) {
            viewModel.scrollPos.postValue(launch.flightNumber!!-1)
        }
        findNavController().navigate(com.example.spacexlaunches.R.id.action_launchList_to_launchDetail)
    }

}
