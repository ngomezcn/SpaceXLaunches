package com.example.spacexlaunches.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexlaunches.adapter.LaunchAdapter
import com.example.spacexlaunches.databinding.FragmentLaunchListBinding
import com.example.spacexlaunches.model.models.Launch
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.viewmodel.ViewModel

class LaunchListFragment : Fragment(), OnClickListener {

    lateinit var binding: FragmentLaunchListBinding
    private lateinit var launchAdapter: LaunchAdapter
    private val viewModel: ViewModel by activityViewModels()
    private var layoutType = 0 //0 - LinearLayout, 1 - GridLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLaunchListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.value?.let {
            setUpRecyclerView(it, layoutType)
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {

            println("IT SIZEEEEEEEEEEEEEEEEEEEEEe")
            println(it.size)

            if(viewModel.data.value == null){
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
            else{
                setUpRecyclerView(it, layoutType)
            }
        })
    }

    private fun setUpRecyclerView(myData: List<Launch>, layoutType: Int) {
        launchAdapter = LaunchAdapter(myData, this)
        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = if(layoutType==0) LinearLayoutManager(context) else GridLayoutManager(context, 2)
            adapter = launchAdapter
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onClick(launch: Launch) {
        viewModel.setLaunch(launch)
    }
}