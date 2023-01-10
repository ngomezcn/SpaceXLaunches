package com.example.spacexlaunches.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.R
import com.example.spacexlaunches.databinding.FragmentDetailBinding
import com.example.spacexlaunches.models.Launch.LaunchModel
import com.example.spacexlaunches.models.LaunchpadModel
import com.example.spacexlaunches.models.Rocket.RocketModel
import com.example.spacexlaunches.viewmodel.ViewModel


class DetailFragment : Fragment(), OnClickListener {
    lateinit var binding: FragmentDetailBinding
    private val viewModel: ViewModel by activityViewModels()

    var webLink = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.actualLaunch.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            setLaunchData(it)
        })

        viewModel.rocket.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            setRocketData(it)
        })

        viewModel.launchpad.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            setLaunchpadData(it)
        })

        binding.seeMoreBtn.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webLink))
            startActivity(browserIntent)
        }
    }

    private fun setLaunchData(it: LaunchModel) {

        // Mission name
        binding.missionNameTextView.text = it.name

        // Web link
        try {

            if(it.links!!.webcast != null)
                webLink = it.links!!.webcast.toString()
            else if(it.links!!.wikipedia != null)
                webLink = it.links!!.wikipedia.toString()
            else if(it.links!!.article != null)
                webLink = it.links!!.article.toString()
            else if(it.links!!.reddit!!.launch != null)
                webLink = it.links!!.reddit!!.launch.toString()
            else if(it.links!!.reddit!!.campaign != null)
                webLink = it.links!!.reddit!!.campaign.toString()

        } catch (ex: Exception)
        {
            binding.seeMoreBtn.visibility = View.GONE
        }
        if(webLink == "") {
            binding.seeMoreBtn.visibility = View.GONE
        }

        // Date and UTC Date
        if(it.dateUtc.isNullOrBlank()) {
            binding.launchDateUTCTextView.text = "---"
            binding.missionDateTextView.text = "---"
        } else
        {
            binding.launchDateUTCTextView.text = it.dateUtc!!
            binding.missionDateTextView.text = it.dateUtc!!.take(10)
        }

        // Details message
        if(it.details.isNullOrEmpty()) {
            if(it.failures.size > 0) {
                binding.messageTextView.text = it.failures.first().reason
            } else
            {
                binding.messageTextView.text = "No details"
            }
        } else
        {
            binding.messageTextView.text = it.details
        }

        // Status
        println(it.success)
        if(it.success != null)
        {
            if(it.success!!)
                binding.missionStatusTextView.text = "Successful"
            else
                binding.missionStatusTextView.text = "Unsuccessful"
        } else
        {
            binding.missionStatusTextView.text = "No data"
        }

        // Flight number
        binding.flightNumberTextView.text = "#" + it.flightNumber.toString()

        // Image
        if(it.links != null) {
            if(it.links!!.patch != null ) {
                if(it.links!!.patch!!.large != null) {
                    Glide.with(this).load(it.links!!.patch!!.large).into(binding.badgeImageView);
                }
            }
        }
    }

    private fun setRocketData(it: RocketModel) {
        binding.rocketNameTextView.text = it.name
        binding.rocketSuccessRateTextView.text = it.success_rate_pct.toString() + "%"
        binding.costPerLaunchTextView.text = java.text.NumberFormat.getIntegerInstance().format(it.cost_per_launch) + "$"
    }

    private fun setLaunchpadData(it: LaunchpadModel) {
        binding.launchSiteTextView.text = it.name
    }

    override fun onClick(launch: LaunchModel) {
    }


}