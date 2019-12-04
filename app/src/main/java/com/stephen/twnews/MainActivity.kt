package com.stephen.twnews

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var tabsTitle: Array<String>? = null
    private var TAG = MainActivity::class.java.simpleName
    private var fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        tabsTitle = resources.getStringArray(R.array.tabs_title)
        Log.d(TAG, "MainActivity: ${(tabsTitle as Array<String>).size} ");

        fragments.add(HeadlineFragment.instance)
        fragments.add(BusinessFragment.instance)
        fragments.add(EntertainmentFragment.instance)
        fragments.add(HealthFragment.instance)
        fragments.add(ScienceFragment.instance)

        val pagerAdapter = NewsPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
        tabs.setupWithViewPager(pager)


    }

    inner class NewsPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = tabsTitle!!.size

        override fun getPageTitle(position: Int): CharSequence? = tabsTitle?.get(position)

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }
    }

}
