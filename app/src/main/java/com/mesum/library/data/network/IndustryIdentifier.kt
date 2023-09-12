package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class IndustryIdentifier(
    @SerializedName("identifier")
    var identifier: String? = "",
    @SerializedName("type")
    var type: String? = ""
)