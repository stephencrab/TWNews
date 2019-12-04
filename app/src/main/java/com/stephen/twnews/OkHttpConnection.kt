package com.stephen.twnews

import android.app.Activity
import android.util.Log
import androidx.core.app.ActivityCompat
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class OkHttpConnection {

        private val TAG = "OkHttpConnection"
        var articles = mutableListOf<Article>()

        fun getData(path: String) {
            var json: String? = null
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://newsapi.org/v2/$path")
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(TAG, "onFailure: ");
                }
                override fun onResponse(call: Call, response: Response) {
                    json = response.body?.string()

                }
            })

        }

        fun parseJSON(json: String?) : MutableList<Article>{
                val jsonObject = JSONObject(json)
                val array = jsonObject.getJSONArray("articles")
                for (i in 0 until array.length()) {
                    val article = array.getJSONObject(i)
                    articles.add(Article(article))
                }
            return articles
        }

}