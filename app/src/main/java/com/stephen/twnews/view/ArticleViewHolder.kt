package com.stephen.twnews.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stephen.twnews.model.Article
import kotlinx.android.synthetic.main.cardview_article.view.*

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var cardView = view.cardview

    fun bindTo(article: Article) {
        cardView.title.setText(article.title)
        cardView.author.setText(article.author)
        Glide.with(cardView.context)
            .load(article.urlToImage)
            .error(android.R.drawable.stat_notify_error)
            .centerCrop()
            .override(300)
            .into(cardView.imageView)
    }

}