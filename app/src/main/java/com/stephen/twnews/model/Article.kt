package com.stephen.twnews.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Article(@SerializedName("author")
                   val author: String = "",
                   @SerializedName("title")
                   val title: String = "",
                   @SerializedName("description")
                   val description: String = "",
                   @SerializedName("urlToImage")
                   val urlToImage: String = "",
                   @SerializedName("publishedAt")
                   val publishedAt: String = "",
                   @SerializedName("url")
                   val url: String = ""
                    ) {
    constructor(newsObject: JSONObject) : this(
        newsObject.getString("author"),
        newsObject.getString("title"),
        newsObject.getString("description"),
        newsObject.getString("urlToImage"),
        newsObject.getString("publishedAt"),
        newsObject.getString("url"))
}