package com.example.spacexlaunches.room

import android.app.Application
import androidx.room.Room

class MissionsListApplication: Application() {
    companion object {
        lateinit var database: MissionsDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            MissionsDatabase::class.java,
            "MissionsDatabase_TEST1").build()
    }
}