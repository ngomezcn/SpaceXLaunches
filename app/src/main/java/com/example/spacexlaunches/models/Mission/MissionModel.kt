package com.example.spacexlaunches.models.Mission

import com.google.gson.annotations.SerializedName

data class MissionModel (
    @SerializedName("links"                 ) var links              : Links?              = Links(),
    @SerializedName("rocket"                ) var rocket             : String?             = null,
    @SerializedName("success"               ) var success            : Boolean?            = null,
    @SerializedName("failures"              ) var failures           : ArrayList<Failures> = arrayListOf(),
    @SerializedName("details"               ) var details            : String?             = null,
    @SerializedName("launchpad"             ) var launchpad          : String?             = null,
    @SerializedName("flight_number"         ) var flightNumber       : Int?                = null,
    @SerializedName("name"                  ) var name               : String?             = null,
    @SerializedName("date_utc"              ) var dateUtc            : String?             = null,
    @SerializedName("id"                    ) var id                 : String?             = null,
)

