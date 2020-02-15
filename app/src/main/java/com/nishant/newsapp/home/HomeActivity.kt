package com.nishant.newsapp.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.nishant.newsapp.R
import com.nishant.newsapp.isVisible
import com.nishant.newsapp.nonEmptyStringOrNull
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private var vm: HomeVM? = null
    private var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        vm = HomeVM(this.application)

        vm?.newsLiveData?.observe(this, Observer { result ->
            if (result.isLoading()) {
                txt_info.isVisible = true
                txt_info.text = "Loading"
            } else if (result.isSuccess()) {
                if (result.data?.articles?.isNotEmpty() == true) {
                    if (adapter == null) {
                        adapter = NewsAdapter(result.data.articles)
                        recyclewView.adapter = adapter
                        if (recyclewView.itemDecorationCount == 0) {
                            recyclewView.addItemDecoration(
                                DividerItemDecoration(
                                    this,
                                    LinearLayout.VERTICAL
                                )
                            )
                        }
                    } else {
                        adapter?.updateData(result.data.articles)
                    }
                    txt_info.isVisible = false
                } else {
                    txt_info.isVisible = true
                    txt_info.text = "No Search Results found for \"${searchView.query}\""
                }
            } else {
                val errorMessage =
                    result.error?.message.nonEmptyStringOrNull() ?: "Something went wrong."
                txt_info.text = errorMessage
                txt_info.isVisible = true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.nonEmptyStringOrNull() != null)
                    vm?.fetchNewsData(newText)
                else
                    vm?.fetchTopHeadlines()
                return true
            }
        })

        vm?.fetchTopHeadlines()
    }
}
