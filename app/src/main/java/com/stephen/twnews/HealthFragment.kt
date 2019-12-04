package com.stephen.twnews

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_health.*
import okhttp3.*
import java.io.IOException

class HealthFragment : Fragment() {

    var articles = mutableListOf<Article>()

    companion object {
        val instance : HealthFragment by lazy {
            HealthFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=tw&category=health&apiKey=e06c56d9996146c58143b54fc48650d7")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }
            override fun onResponse(call: Call, response: Response) {
                articles = OkHttpConnection().parseJSON(response.body?.string())
                activity?.runOnUiThread {
                    recycler.adapter = ArticleAdapter(articles)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recycler = inflater.inflate(R.layout.fragment_health, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)

        return recycler
    }


}