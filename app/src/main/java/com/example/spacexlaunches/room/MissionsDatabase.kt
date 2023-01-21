package com.example.spacexlaunches.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spacexlaunches.room.DAOs.MissionDAO
import com.example.spacexlaunches.room.Entities.LaunchEntity

@Database(entities = [LaunchEntity::class/*, RocketEntity::class*/], version = 3)
abstract class MissionsDatabase: RoomDatabase() {
    abstract fun launchDao(): MissionDAO
}
