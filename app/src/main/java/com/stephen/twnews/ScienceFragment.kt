package com.stephen.twnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_science.*
import okhttp3.*
import java.io.IOException

class ScienceFragment : Fragment() {

    var articles = mutableListOf<Article>()

    companion object {
        val instance : ScienceFragment by lazy {
            ScienceFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=tw&category=science&apiKey=e06c56d9996146c58143b54fc48650d7")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
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

        val recycler = inflater.inflate(R.layout.fragment_science, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)

        return recycler
    }
}