package com.example.spacexlaunches.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Launch(var url: String, var missionName: String, var rocketName: String, var date: String) : Parcelable
