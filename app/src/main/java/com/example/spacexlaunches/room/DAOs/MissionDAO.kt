package com.example.spacexlaunches.room.DAOs

import androidx.room.*
import com.example.spacexlaunches.models.Mission.MissionModel
import com.example.spacexlaunches.room.Entities.LaunchEntity

@Dao
interface MissionDAO {
    @Query("SELECT * FROM LaunchEntity")
    fun getAll(): List<LaunchEntity>

    @Query("SELECT * FROM LaunchEntity T0 WHERE T0.flightNumber = :fn")
    fun get(fn : Int): LaunchEntity?

    @Insert
    fun add(launchEntity: LaunchEntity)

    fun add(oLaunch: MissionModel)
    {
        val launchEntity = LaunchEntity(
            name = oLaunch.name,
            dateUtc = oLaunch.dateUtc,
            failures_reason =  if (oLaunch.failures.isNotEmpty()) oLaunch.failures.first().reason else null,
            details = oLaunch.details,
            success = oLaunch.success,
            flightNumber = oLaunch.flightNumber,
            links_patch_large =  oLaunch.links?.patch?.large,
            links_patch_small = oLaunch.links?.patch?.small,
            rocketId = 0
        )

        if(oLaunch.failures.size > 1) {
            launchEntity.failures_reason = oLaunch.failures.first().reason!!
        }

        this.add(launchEntity)
    }

    @Query("DELETE FROM LaunchEntity WHERE flightNumber = :fn")
    fun delete(fn : Int)
}