package com.mesum.library.data.network


import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("allowAnonLogging")
    var allowAnonLogging: Boolean? = false,
    @SerializedName("authors")
    var authors: List<String?>? = listOf(),
    @SerializedName("averageRating")
    var averageRating: Double? = 0.0,
    @SerializedName("canonicalVolumeLink")
    var canonicalVolumeLink: String? = "",
    @SerializedName("categories")
    var categories: List<String?>? = listOf(),
    @SerializedName("comicsContent")
    var comicsContent: Boolean? = false,
    @SerializedName("contentVersion")
    var contentVersion: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("imageLinks")
    var imageLinks: ImageLinks? = ImageLinks(),
    @SerializedName("industryIdentifiers")
    var industryIdentifiers: List<IndustryIdentifier?>? = listOf(),
    @SerializedName("infoLink")
    var infoLink: String? = "",
    @SerializedName("language")
    var language: String? = "",
    @SerializedName("maturityRating")
    var maturityRating: String? = "",
    @SerializedName("pageCount")
    var pageCount: Int? = 0,
    @SerializedName("panelizationSummary")
    var panelizationSummary: PanelizationSummary? = PanelizationSummary(),
    @SerializedName("previewLink")
    var previewLink: String? = "",
    @SerializedName("printType")
    var printType: String? = "",
    @SerializedName("publishedDate")
    var publishedDate: String? = "",
    @SerializedName("publisher")
    var publisher: String? = "",
    @SerializedName("ratingsCount")
    var ratingsCount: Int? = 0,
    @SerializedName("readingModes")
    var readingModes: ReadingModes? = ReadingModes(),
    @SerializedName("subtitle")
    var subtitle: String? = "",
    @SerializedName("title")
    var title: String? = ""
)