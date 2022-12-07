package com.example.spacexlaunches

import com.example.spacexlaunches.model.models.Launch

interface OnClickListener {
    fun onClick(launch: Launch)
}