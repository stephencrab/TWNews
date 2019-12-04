package com.stephen.twnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_headline.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

class HeadlineFragment() : Fragment() {

    private val TAG: String? = HeadlineFragment::class.java.simpleName
    var articles = mutableListOf<Article>()


    companion object {
        val instance : HeadlineFragment by lazy {
            HeadlineFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=tw&apiKey=e06c56d9996146c58143b54fc48650d7")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "Okhttp: Fail ");
            }
            override fun onResponse(call: Call, response: Response) {
                var json = response.body?.string()
                Log.d(TAG, "onResponse : $json ");
                activity?.runOnUiThread {
                    articles = OkHttpConnection().parseJSON(json)
                    recycler.adapter = ArticleAdapter(articles)
                }
            }
        })
        Log.d(TAG, ": Headline");
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recycler = inflater.inflate(R.layout.fragment_headline, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)
        Log.d(TAG, "onCreateView: ");

        return recycler
    }

}