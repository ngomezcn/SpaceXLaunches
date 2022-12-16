package com.example.spacexlaunches.models.Launch


import com.google.gson.annotations.SerializedName


data class Crew (

    @SerializedName("crew" ) var crew : String? = null,
    @SerializedName("role" ) var role : String? = null

)