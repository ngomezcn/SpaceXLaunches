package com.example.spacexlaunches.models.Launch

import com.google.gson.annotations.SerializedName

data class LaunchModel (

   // @SerializedName("fairings"              ) var fairings           : Fairings?           = Fairings(),
    @SerializedName("links"                 ) var links              : Links?              = Links(),
    @SerializedName("static_fire_date_utc"  ) var staticFireDateUtc  : String?             = null,
    @SerializedName("static_fire_date_unix" ) var staticFireDateUnix : Int?                = null,
    @SerializedName("net"                   ) var net                : Boolean?            = null,
    @SerializedName("window"                ) var window             : Int?                = null,
    @SerializedName("rocket"                ) var rocket             : String?             = null,
    @SerializedName("success"               ) var success            : Boolean?            = null,
    @SerializedName("failures"              ) var failures           : ArrayList<Failures> = arrayListOf(),
    @SerializedName("details"               ) var details            : String?             = null,
   // @SerializedName("crew"                  ) var crew               : ArrayList<String>   = arrayListOf(),
    //@SerializedName("ships"                 ) var ships              : ArrayList<String>   = arrayListOf(),
   // @SerializedName("capsules"              ) var capsules           : ArrayList<String>   = arrayListOf(),
   // @SerializedName("payloads"              ) var payloads           : ArrayList<String>   = arrayListOf(),
    @SerializedName("launchpad"             ) var launchpad          : String?             = null,
    @SerializedName("flight_number"         ) var flightNumber       : Int?                = null,
    @SerializedName("name"                  ) var name               : String?             = null,
    @SerializedName("date_utc"              ) var dateUtc            : String?             = null,
    @SerializedName("date_unix"             ) var dateUnix           : Int?                = null,
    @SerializedName("date_local"            ) var dateLocal          : String?             = null,
    @SerializedName("date_precision"        ) var datePrecision      : String?             = null,
    @SerializedName("upcoming"              ) var upcoming           : Boolean?            = null,
    @SerializedName("cores"                 ) var cores              : ArrayList<Cores>    = arrayListOf(),
    @SerializedName("auto_update"           ) var autoUpdate         : Boolean?            = null,
    @SerializedName("tbd"                   ) var tbd                : Boolean?            = null,
    @SerializedName("launch_library_id"     ) var launchLibraryId    : String?             = null,
    @SerializedName("id"                    ) var id                 : String?             = null,
    val isFav : Boolean = false
)