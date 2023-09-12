package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("isEbook")
    var isEbook: Boolean? = false,
    @SerializedName("saleability")
    var saleability: String? = ""
)