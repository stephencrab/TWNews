package com.stephen.twnews.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stephen.twnews.model.Article
import com.stephen.twnews.R
import com.stephen.twnews.WebViewActivity

class ArticleAdapter(val articles: List<Article>) : RecyclerView.Adapter<ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview_article,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var article = articles.get(position)
        holder.bindTo(article)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WebViewActivity::class.java)
            intent.putExtra("NEWS_URL", articles[position].url)
            holder.itemView.context.startActivity(intent)
        }
    }


}