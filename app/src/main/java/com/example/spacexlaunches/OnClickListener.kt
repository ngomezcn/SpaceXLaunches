package com.example.spacexlaunches

import com.example.spacexlaunches.model.models.LaunchModel

interface OnClickListener {
    fun onClick(launch: LaunchModel)
}