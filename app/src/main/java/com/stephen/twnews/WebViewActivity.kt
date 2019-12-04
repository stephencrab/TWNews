package com.stephen.twnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val newsUrl= intent.getStringExtra("NEWS_URL")

        web_view.settings.javaScriptEnabled = true
        setContentView(web_view)
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(newsUrl)
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
            return
        }
        super.onBackPressed()
    }
}
