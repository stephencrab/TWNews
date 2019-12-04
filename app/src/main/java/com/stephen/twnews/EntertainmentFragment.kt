package com.stephen.twnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_entertainment.*
import okhttp3.*
import java.io.IOException

class EntertainmentFragment : Fragment() {

    private val TAG = EntertainmentFragment::class.java.simpleName
    var articles = mutableListOf<Article>()

    companion object {
        val instance : EntertainmentFragment by lazy {
            EntertainmentFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ");

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=tw&category=entertainment&apiKey=e06c56d9996146c58143b54fc48650d7")
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

        val recycler = inflater.inflate(R.layout.fragment_entertainment, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)

        return recycler
    }
}