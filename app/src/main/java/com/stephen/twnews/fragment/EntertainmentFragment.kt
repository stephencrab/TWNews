package com.stephen.twnews.fragment

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_entertainment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntertainmentFragment : Fragment() {

    private val TAG = EntertainmentFragment::class.java.simpleName
    var articles = mutableListOf<Article>()
    val apiKey = "e06c56d9996146c58143b54fc48650d7"
    val country = "tw"
    val category = "entertainment"

    companion object {
        val instance : EntertainmentFragment by lazy {
            EntertainmentFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        NewsClient.getClient.create(NewsApi::class.java)
            .getCategory(country, category, apiKey).enqueue(object : Callback<News> {
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

        val recycler = inflater.inflate(R.layout.fragment_entertainment, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)
        recycler.adapter = ArticleAdapter(articles)

        return recycler
    }
}