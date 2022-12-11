package com.example.spacexlaunches.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Links (

    @SerializedName("patch"      ) var patch     : Patch?  = Patch(),
    /*@SerialName("reddit"     ) var reddit    : Reddit? = Reddit(),
    @SerialName("flickr"     ) var flickr    : Flickr? = Flickr(),
    @SerialName("presskit"   ) var presskit  : String? = null,
    @SerialName("webcast"    ) var webcast   : String? = null,
    @SerialName("youtube_id" ) var youtubeId : String? = null,
    @SerialName("article"    ) var article   : String? = null,
    @SerialName("wikipedia"  ) var wikipedia : String? = null*/
) : Parcelable