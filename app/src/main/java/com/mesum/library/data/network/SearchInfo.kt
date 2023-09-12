package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class SearchInfo(
    @SerializedName("textSnippet")
    var textSnippet: String? = ""
)