package com.example.spacexlaunches.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.adapter.LaunchAdapter
import com.example.spacexlaunches.databinding.FragmentLaunchListBinding
import com.example.spacexlaunches.model.models.Launch
import com.example.spacexlaunches.viewmodel.ViewModel
import java.util.*

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
        viewModel.data.value?.let {
            setUpRecyclerView(it)
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            if(viewModel.data.value == null){
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
            else{
                setUpRecyclerView(it)
            }
        })
    }

    private fun setUpRecyclerView(myData: List<Launch>) {
        launchAdapter = LaunchAdapter(myData, this)
        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = GridLayoutManager(context, 1)
            adapter = launchAdapter
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onClick(launch: Launch) {
        viewModel.setLaunch(launch)
        findNavController().navigate(com.example.spacexlaunches.R.id.action_launchList_to_launchDetail)
    }

}