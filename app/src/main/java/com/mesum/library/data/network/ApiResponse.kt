package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("items")
    var items: List<Item?>? = listOf(),
    @SerializedName("kind")
    var kind: String? = "",
    @SerializedName("totalItems")
    var totalItems: Int? = 0
)