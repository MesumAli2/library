package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("acsTokenLink")
    var acsTokenLink: String? = "",
    @SerializedName("isAvailable")
    var isAvailable: Boolean? = false
)