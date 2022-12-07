package com.example.spacexlaunches.model.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Patch (

    @SerialName("small" ) var small : String? = null,
    @SerialName("large" ) var large : String? = null
) : Parcelable