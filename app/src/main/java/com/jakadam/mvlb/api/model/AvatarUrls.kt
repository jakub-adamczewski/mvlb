package com.jakadam.mvlb.api.model

import com.google.gson.annotations.SerializedName

data class AvatarUrls(
    @SerializedName("24")
    val one: String,

    @SerializedName("48")
    val two: String,

    @SerializedName("96")
    val three: String
)