package com.example.spacexlaunches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacexlaunches.databinding.ActivityMainBinding
import com.example.spacexlaunches.fragments.LaunchListFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, LaunchListFragment())
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
            commit()
        }

    }
}