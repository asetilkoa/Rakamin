package com.example.rakamin.network

import com.example.rakamin.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getHeadlines(
        @Query ("country") country: String?,
        @Query ("apiKey") apiKey: String?
    ): Call<NewsModel>

    @GET("everything")
    fun getEverything(
        @Query("q") keyword: String?,
        @Query ("country") country: String?,
        @Query ("page") page: Int= 1,
        @Query ("pageSize") pageSize: Int= 1,
        @Query("apiKey") apiKey: String?
    ): Call<NewsModel>
}