package com.example.spacexlaunches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexlaunches.adapter.LaunchAdapter
import com.example.spacexlaunches.databinding.FragmentLaunchListBinding
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.model.LaunchViewModel
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class LaunchListFragment : Fragment(), OnClickListener {

    lateinit var binding: FragmentLaunchListBinding
    private lateinit var launchAdapter: LaunchAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val model: LaunchViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLaunchListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchAdapter = LaunchAdapter(model.launchList.value!!, this)
        linearLayoutManager = LinearLayoutManager(context)

        binding.recycler.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = linearLayoutManager
            adapter = launchAdapter
        }

        model.launchList.observe(viewLifecycleOwner, Observer {
            launchAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(launch: Launch) {
        model.select(launch)
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, DetailFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }
    }

    /*private fun getLaunches(): MutableList<Launch>{
        val launches = mutableListOf<Launch>()
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon SAT 1", "Falcon 1", "2006-03-25"))
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon SAT 2", "Falcon 1", "2007-03-21"))
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Trailblazer", "Falcon 1", "2008-08-03"))
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","RatSat", "Falcon 1", "2008-09-28"))
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon 9 Test Flight", "Falcon 1", "2010-12-08"))
        launches.add(Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Cots 1", "Falcon 9", "2010-12-08"))

        return launches
    }*/
}