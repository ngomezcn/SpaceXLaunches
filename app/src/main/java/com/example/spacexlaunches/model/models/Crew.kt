package com.example.spacexlaunches.model.models


import kotlinx.serialization.SerialName


data class Crew (

    @SerialName("crew" ) var crew : String? = null,
    @SerialName("role" ) var role : String? = null

)