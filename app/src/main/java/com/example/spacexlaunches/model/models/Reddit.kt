package com.example.spacexlaunches.model.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class Reddit (

    @SerializedName("campaign" ) var campaign : String? = null,
    @SerializedName("launch"   ) var launch   : String? = null,
    @SerializedName("media"    ) var media    : String? = null,
    @SerializedName("recovery" ) var recovery : String? = null
)