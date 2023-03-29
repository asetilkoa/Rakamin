package com.example.rakamin.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName ("status")
    val status: String = "",

    @SerializedName ("totalResults")
    val totalResults: String = "",

    @SerializedName ("articles")
    val articles: List<ArticleModel> =  emptyList()
)
