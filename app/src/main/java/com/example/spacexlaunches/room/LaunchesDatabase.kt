package com.example.spacexlaunches.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spacexlaunches.room.DAOs.LaunchDAO
import com.example.spacexlaunches.room.DAOs.RocketDAO
import com.example.spacexlaunches.room.Entities.LaunchEntity
import com.example.spacexlaunches.room.Entities.RocketEntity

@Database(entities = [LaunchEntity::class, RocketEntity::class], version = 2)
abstract class LaunchesDatabase: RoomDatabase() {
    abstract fun launchDao(): LaunchDAO
    abstract fun rocketDao(): RocketDAO
}
