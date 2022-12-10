package com.example.spacexlaunches.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spacexlaunches.databinding.FragmentDetailBinding
import com.example.spacexlaunches.viewmodel.ViewModel

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.actualLaunch.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.missionNameTextView.text = it.name
            binding.missionComment.text = it.name
            binding.launchSiteTextView.text = it.name
            binding.payloadTextView.text = it.name
            binding.payloadKGTextView.text = it.name
            binding.orbitTextView.text = it.name
            binding.orbitPeriapsisTextView.text = it.name
            binding.orbitApoapsisTextView.text = it.name
        })
    }
}