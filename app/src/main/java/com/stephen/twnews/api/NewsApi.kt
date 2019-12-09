package com.stephen.twnews.api

import com.stephen.twnews.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getHeadline(
        @Query("country") country: String = "tw",
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("top-headlines")
    fun getCategory(
        @Query("country") country: String = "tw",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<News>
}
