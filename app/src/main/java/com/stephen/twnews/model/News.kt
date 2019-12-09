package com.stephen.twnews.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val totalResults: Int = 0,
    @SerializedName("articles") val articles: MutableList<Article>
                )