package com.example.spacexlaunches.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Patch (

    @SerializedName("small" ) var small : String? = null,
    @SerializedName("large" ) var large : String? = null
) : Parcelable