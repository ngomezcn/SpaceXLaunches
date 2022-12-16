package com.example.spacexlaunches.models

data class LaunchpadModel(
    val details: String,
    val full_name: String,
    val id: String,
    val images: Images,
    val latitude: Double,
    val launch_attempts: Int,
    val launch_successes: Int,
    val launches: List<String>,
    val locality: String,
    val longitude: Double,
    val name: String,
    val region: String,
    val rockets: List<String>,
    val status: String,
    val timezone: String
)

data class Images(
    val large: List<String>
)