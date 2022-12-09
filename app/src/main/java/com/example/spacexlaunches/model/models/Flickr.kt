package com.example.spacexlaunches.model.models

import kotlinx.serialization.SerialName

data class Flickr (

    @SerialName("small"    ) var small    : ArrayList<String> = arrayListOf(),
    @SerialName("original" ) var original : ArrayList<String> = arrayListOf()

)