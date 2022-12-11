package com.example.spacexlaunches.model.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class Flickr (

    @SerializedName("small"    ) var small    : ArrayList<String> = arrayListOf(),
    @SerializedName("original" ) var original : ArrayList<String> = arrayListOf()

)