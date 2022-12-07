package com.example.spacexlaunches.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spacexlaunches.databinding.FragmentDetailBinding
import com.example.spacexlaunches.databinding.FragmentLaunchListBinding
import com.example.spacexlaunches.model.LaunchViewModel

class DetailFragment : Fragment() {
    private val model: LaunchViewModel by activityViewModels()
    lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        binding.text.text = model.selectedUser.value!!.name
        return binding.root
    }
}