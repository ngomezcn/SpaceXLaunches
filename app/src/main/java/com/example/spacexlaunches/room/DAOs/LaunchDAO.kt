package com.example.spacexlaunches.room.DAOs

import androidx.room.*
import com.example.spacexlaunches.room.Entities.LaunchEntity

@Dao
interface LaunchDAO {
    @Query("SELECT * FROM LaunchEntity")
    fun getAll(): MutableList<LaunchEntity>
    @Insert
    fun add(launchEntity: LaunchEntity)
    @Update
    fun update(launchEntity: LaunchEntity)
    @Delete
    fun delete(launchEntity: LaunchEntity)
}