package com.example.spacexlaunches

import com.example.spacexlaunches.models.Launch.LaunchModel

interface OnClickListener {
    fun onClick(launch: LaunchModel)
}