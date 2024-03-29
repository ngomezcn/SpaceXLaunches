package com.example.spacexlaunches.models.Mission

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flickr (

    @SerializedName("small"    ) var small    : ArrayList<String> = arrayListOf(),
    @SerializedName("original" ) var original : ArrayList<String> = arrayListOf()

) : Parcelable