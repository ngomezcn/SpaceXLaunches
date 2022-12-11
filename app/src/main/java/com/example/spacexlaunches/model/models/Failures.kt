package com.example.spacexlaunches.model.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class Failures (
    @SerializedName("time"     ) var time     : Int?    = null,
    @SerializedName("altitude" ) var altitude : String? = null,
    @SerializedName("reason"   ) var reason   : String? = null
)