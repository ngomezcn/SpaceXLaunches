package com.example.spacexlaunches.room.Entities

import android.app.Application
import androidx.room.*

@Entity(tableName = "LaunchEntity",
    foreignKeys = [ForeignKey(entity = RocketEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("rocketId"),
        onDelete = ForeignKey.SET_NULL)]
)
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    var dateUtc: String,
    var failures_reason: String,
    var details: String,
    var success: Boolean,
    var flightNumber: Int,
    var links_patch_large: String,
    var rocketId: Long)






