package com.example.spacexlaunches.model.models

import kotlinx.serialization.SerialName

data class Failures (
    @SerialName("time"     ) var time     : Int?    = null,
    @SerialName("altitude" ) var altitude : String? = null,
    @SerialName("reason"   ) var reason   : String? = null
)