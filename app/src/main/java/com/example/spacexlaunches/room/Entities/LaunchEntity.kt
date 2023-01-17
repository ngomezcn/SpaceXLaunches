package com.example.spacexlaunches.room.Entities

import android.app.Application
import androidx.room.*

@Entity(tableName = "LaunchEntity")
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    var name: String?,
    var dateUtc: String?,
    var failures_reason: String?,
    var details: String?,
    var success: Boolean?,
    var flightNumber: Int?,
    var links_patch_large: String?,
    var links_patch_small: String?,
    var rocketId: Long?)






