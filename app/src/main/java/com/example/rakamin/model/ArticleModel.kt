package com.example.rakamin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    @SerializedName ("source")
    var sourcemodel: SourceModel?,

    @SerializedName ("author")
    var author: String = "",

    @SerializedName ("title")
    var title: String = "",

    @SerializedName ("description")
    var description: String = "",

    @SerializedName ("url")
    var url: String = "",

    @SerializedName ("urlToImage")
    var urlToImage: String = "",

    @SerializedName ("publishedAt")
    var publishedAt: String = "",

    @SerializedName ("content")
    var content: String = ""
) : Parcelable

@Parcelize
data class SourceModel(
    @SerializedName ("id")
    val id: String = "",

    @SerializedName ("name")
    val name: String = ""
) : Parcelable
