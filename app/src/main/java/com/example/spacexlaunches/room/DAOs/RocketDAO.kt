package com.example.spacexlaunches.room.DAOs

import androidx.room.*
import com.example.spacexlaunches.room.Entities.RocketEntity

@Dao
interface RocketDAO {
    @Query("SELECT * FROM RocketEntity")
    fun getAll(): MutableList<RocketEntity>

    @Insert
    fun add(launchEntity: RocketEntity) : Long

    @Update
    fun update(launchEntity: RocketEntity)

    @Delete
    fun delete(launchEntity: RocketEntity)
}