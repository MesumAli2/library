package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class PanelizationSummary(
    @SerializedName("containsEpubBubbles")
    var containsEpubBubbles: Boolean? = false,
    @SerializedName("containsImageBubbles")
    var containsImageBubbles: Boolean? = false
)