package com.example.spacexlaunches.model.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class Cores (

    @SerializedName("core"            ) var core           : String?  = null,
    @SerializedName("flight"          ) var flight         : Int?     = null,
    @SerializedName("gridfins"        ) var gridfins       : Boolean? = null,
    @SerializedName("legs"            ) var legs           : Boolean? = null,
    @SerializedName("reused"          ) var reused         : Boolean? = null,
    @SerializedName("landing_attempt" ) var landingAttempt : Boolean? = null,
    @SerializedName("landing_success" ) var landingSuccess : Boolean? = null,
    @SerializedName("landing_type"    ) var landingType    : String?  = null,
    @SerializedName("landpad"         ) var landpad        : String?  = null

)