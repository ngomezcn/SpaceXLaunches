package com.example.spacexlaunches.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.spacexlaunches.MainActivity
import com.example.spacexlaunches.OnClickListener
import com.example.spacexlaunches.R
import com.example.spacexlaunches.databinding.ItemLayoutBinding
import com.example.spacexlaunches.models.Mission.MissionModel
import com.example.spacexlaunches.room.MissionsListApplication
import com.example.spacexlaunches.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LaunchAdapter (private var missions: List<MissionModel>, private val listener: OnClickListener, private val fcontext: FragmentActivity?):
    RecyclerView.Adapter<LaunchAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println(missions[position])

        val mission = missions[position]

        CoroutineScope(Dispatchers.IO).launch {
            if (MissionsListApplication.database.launchDao().get(mission.flightNumber!!) != null
            ) {
                holder.binding.pinnedButton.setImageResource(R.drawable.fav_pin_blue);
            } else
            {
                holder.binding.pinnedButton.setImageResource(R.drawable.fav_pin_white);
            }
        }

        val _this = this
        holder.binding.pinnedButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                if(MissionsListApplication.database.launchDao().get(mission.flightNumber!!) == null) {
                    MissionsListApplication.database.launchDao().add(mission)
                    holder.binding.pinnedButton.setImageResource(R.drawable.fav_pin_blue);

                } else
                {
                    MissionsListApplication.database.launchDao().delete(mission.flightNumber!!)
                    holder.binding.pinnedButton.setImageResource(R.drawable.fav_pin_white);

                    fcontext?.runOnUiThread(java.lang.Runnable {
                        val a = missions.toMutableList()
                        a.remove(mission)
                        _this.missions = a

                    })
                }
            }
        }

        with(holder){
            setListener(mission)
            binding.missionNameTextView.text = mission.name?.take(18) ?: "-"
            binding.rocketNameTextView.text = mission.flightNumber.toString()

            if(mission.dateUtc.isNullOrBlank()) {
                binding.dateTextView.text = "---"
            } else
            {
                binding.dateTextView.text = mission.dateUtc!!.take(10)
            }

           if(mission.links!!.patch!!.small != null)
            {
                Glide.with(context)
                    .load(mission.links?.patch?.small)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.launchImageImageView)
            } else
            {
                Glide.with(context)
                    .load("https://www.spacex.com/static/images/share.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.launchImageImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return missions.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemLayoutBinding.bind(view)

        fun setListener(launch: MissionModel){
            binding.root.setOnClickListener {
                listener.onClick(launch)
            }
        }
    }
}