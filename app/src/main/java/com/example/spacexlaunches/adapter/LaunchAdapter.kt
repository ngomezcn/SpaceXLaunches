package com.example.spacexlaunches.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacexlaunches.databinding.ItemLayoutBinding
import android.content.Context
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.R
import com.example.spacexlaunches.model.Launch

class LaunchAdapter (private val launches: List<Launch>,
                     private val listener: OnClickListener
):
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launches[position]
        with(holder){
            setListener(launch)
            binding.missionNameTextView.text = launch.missionName
            binding.rocketNameTextView.text = launch.rocketName
            binding.dateTextView.text = launch.date
            Glide.with(context)
                .load(launch.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.launchImageImageView)
        }
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemLayoutBinding.bind(view)

        fun setListener(user: Launch){
            binding.root.setOnClickListener {
                listener.onClick(user)
            }
        }
    }
}