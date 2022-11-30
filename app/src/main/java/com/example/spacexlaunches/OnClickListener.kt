package com.example.spacexlaunches

import com.example.spacexlaunches.model.Launch

interface OnClickListener {
    fun onClick(launch: Launch)
}