package com.example.spacexlaunches.room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RocketEntity")
data class RocketEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var success_rate_pct: Float,
    var cost_per_launch: Int)
