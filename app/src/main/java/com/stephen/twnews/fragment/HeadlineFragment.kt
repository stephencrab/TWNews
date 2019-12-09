package com.stephen.twnews.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stephen.twnews.model.Article
import com.stephen.twnews.R
import com.stephen.twnews.api.NewsApi
import com.stephen.twnews.api.NewsClient
import com.stephen.twnews.model.News
import com.stephen.twnews.view.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_headline.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlineFragment() : Fragment() {
    val apiKey = "e06c56d9996146c58143b54fc48650d7"
    val country = "tw"
    private val TAG: String? = HeadlineFragment::class.java.simpleName
    var articles = mutableListOf<Article>()


    companion object {
        val instance : HeadlineFragment by lazy {
            HeadlineFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NewsClient.getClient.create(NewsApi::class.java)
            .getHeadline(country, apiKey).enqueue(object : Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
               response.body()?.let {
                    articles = it.articles
               }
                recycler.adapter = ArticleAdapter(articles)
            }
        })

        /*val client = OkHttpClient()
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
        })*/
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