package com.example.spacexlaunches.model.models

import kotlinx.serialization.SerialName

data class Fairings (

    @SerialName("reused"           ) var reused          : Boolean?          = null,
    @SerialName("recovery_attempt" ) var recoveryAttempt : Boolean?          = null,
    @SerialName("recovered"        ) var recovered       : Boolean?          = null,
    @SerialName("ships"            ) var ships           : ArrayList<String> = arrayListOf()
)