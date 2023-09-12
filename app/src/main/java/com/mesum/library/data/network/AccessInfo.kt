package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class AccessInfo(
    @SerializedName("accessViewStatus")
    var accessViewStatus: String? = "",
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("embeddable")
    var embeddable: Boolean? = false,
    @SerializedName("epub")
    var epub: Epub? = Epub(),
    @SerializedName("pdf")
    var pdf: Pdf? = Pdf(),
    @SerializedName("publicDomain")
    var publicDomain: Boolean? = false,
    @SerializedName("quoteSharingAllowed")
    var quoteSharingAllowed: Boolean? = false,
    @SerializedName("textToSpeechPermission")
    var textToSpeechPermission: String? = "",
    @SerializedName("viewability")
    var viewability: String? = "",
    @SerializedName("webReaderLink")
    var webReaderLink: String? = ""
)