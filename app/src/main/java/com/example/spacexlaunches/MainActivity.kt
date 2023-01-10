package com.example.spacexlaunches

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.spacexlaunches.databinding.ActivityMainBinding
import com.example.spacexlaunches.room.LaunchApplication
import com.example.spacexlaunches.room.LaunchEntity
import com.example.spacexlaunches.room.RocketEntity
import com.example.spacexlaunches.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragment_container_view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false // Liberamos flujo hacia los fragments
    }

    fun onFavClick(menuItem: MenuItem) {
        if((findNavController(R.id.fragment_container_view).currentDestination?.id ?: -1) == R.id.detailFragment) {
            println(" LAUNCH LIST ESTA ${viewModel.launchList.value!!.size}")

            val launch = viewModel.actualLaunch.value!!
            val rocket = viewModel.rocket.value!!

            val rocketEntity = RocketEntity(
                name = rocket.name,
                success_rate_pct = rocket.success_rate_pct,
                cost_per_launch = rocket.cost_per_launch,
            )

            val launchEntity = LaunchEntity(
                dateUtc = launch.dateUtc!!,
                failures_reason = launch.failures.first().reason!!,
                details = launch.details!!,
                success = launch.success!!,
                flightNumber = launch.flightNumber!!,
                links_patch_large = launch.links!!.patch!!.large!!,
                rocketId = 0
                )

            CoroutineScope(Dispatchers.IO).launch {
                LaunchApplication.database.launchDao().add(launchEntity)
                LaunchApplication.database.rocketDao().add(rocketEntity)

            }
        }
    }
}