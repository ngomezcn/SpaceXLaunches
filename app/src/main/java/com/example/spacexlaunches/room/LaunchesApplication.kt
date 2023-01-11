package com.example.spacexlaunches.room

import android.app.Application
import androidx.room.Room

class LaunchesApplication: Application() {
    companion object {
        lateinit var database: LaunchesDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            LaunchesDatabase::class.java,
            "LaunchessDatabase_ITB1").build()
    }
}