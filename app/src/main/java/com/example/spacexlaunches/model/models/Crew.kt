package com.example.spacexlaunches.model.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName


data class Crew (

    @SerializedName("crew" ) var crew : String? = null,
    @SerializedName("role" ) var role : String? = null

)