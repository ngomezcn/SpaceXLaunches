package com.example.spacexlaunches

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.spacexlaunches.databinding.ActivityMainBinding
import com.example.spacexlaunches.room.LaunchApplication
import com.example.spacexlaunches.room.LaunchEntity
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

            val contactName = "ZZZ"
            val phoneNumber = "NT"
            val newContact = LaunchEntity(name = contactName, phoneNumber = phoneNumber)

            CoroutineScope(Dispatchers.IO).launch {
                LaunchApplication.database.launchDao().add(newContact)

            }
        }
    }
}