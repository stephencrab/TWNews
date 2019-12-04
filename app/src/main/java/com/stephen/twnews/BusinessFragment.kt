package com.stephen.twnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_business.*
import okhttp3.*
import java.io.IOException


class BusinessFragment : Fragment() {

    private val TAG = BusinessFragment::class.java.simpleName
    var articles = mutableListOf<Article>()

    companion object {
        val instance : BusinessFragment by lazy {
            BusinessFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ");
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ");

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=tw&category=business&apiKey=e06c56d9996146c58143b54fc48650d7")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var json = response.body?.string()
                activity?.runOnUiThread {
                    articles = OkHttpConnection().parseJSON(json)
                    recycler.adapter = ArticleAdapter(articles)
                }
            }

        })

        val recycler = inflater.inflate(R.layout.fragment_business, container, false) as RecyclerView

        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)

        return recycler
    }
}