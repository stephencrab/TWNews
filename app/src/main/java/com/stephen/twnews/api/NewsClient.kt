package com.stephen.twnews.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsClient {


    companion object {
        private var newsUrl = "https://newsapi.org/v2/"
        val getClient: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(newsUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}