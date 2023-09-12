package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("accessInfo")
    var accessInfo: AccessInfo? = AccessInfo(),
    @SerializedName("etag")
    var etag: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("kind")
    var kind: String? = "",
    @SerializedName("saleInfo")
    var saleInfo: SaleInfo? = SaleInfo(),
    @SerializedName("searchInfo")
    var searchInfo: SearchInfo? = SearchInfo(),
    @SerializedName("selfLink")
    var selfLink: String? = "",
    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo? = VolumeInfo()
)