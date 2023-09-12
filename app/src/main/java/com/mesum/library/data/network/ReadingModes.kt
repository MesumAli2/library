package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class ReadingModes(
    @SerializedName("image")
    var image: Boolean? = false,
    @SerializedName("text")
    var text: Boolean? = false
)