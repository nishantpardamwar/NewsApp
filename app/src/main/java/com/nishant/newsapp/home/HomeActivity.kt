package com.nishant.newsapp.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.nishant.newsapp.R
import com.nishant.newsapp.ResultWrapper
import com.nishant.newsapp.databinding.ActivityHomeBinding
import com.nishant.newsapp.isVisible
import com.nishant.newsapp.model.NewsResponse
import com.nishant.newsapp.nonEmptyStringOrNull

class HomeActivity : AppCompatActivity() {

    private var viewBinding: ActivityHomeBinding? = null
    private var vm: HomeVM? = null
    private var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        init()
    }

    private fun init() {
        vm = ViewModelProvider.AndroidViewModelFactory(this.application).create(HomeVM::class.java)

        vm?.newsLiveData?.observe(this, Observer { result ->

            handleLoading(result.isLoading)

            when (result.state) {
                ResultWrapper.State.SUCCESS -> {
                    handleSuccess(result.data)
                }

                ResultWrapper.State.FAILURE -> {
                    handleError(result.error)
                }
            }
        })

        viewBinding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm?.fetchNewsData(newText)
                return true
            }
        })

        vm?.fetchNewsData()
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            viewBinding?.txtInfo?.isVisible = true
            viewBinding?.txtInfo?.text = "Loading"
        } else {
            viewBinding?.txtInfo?.isVisible = false
        }
    }

    private fun handleSuccess(data: NewsResponse?) {
        if (data?.articles?.isNotEmpty() == true) {
            if (adapter == null) {
                adapter = NewsAdapter(data.articles)
                viewBinding?.recyclewView?.adapter = adapter
                if (viewBinding?.recyclewView?.itemDecorationCount == 0) {
                    viewBinding?.recyclewView?.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            LinearLayout.VERTICAL
                        )
                    )
                }
            } else {
                adapter?.updateData(data.articles)
            }
        } else {
            viewBinding?.txtInfo?.isVisible = true
            viewBinding?.txtInfo?.text =
                "No Search Results found for \"${viewBinding?.searchView?.query}\""
        }
    }

    private fun handleError(error: Throwable?) {
        val errorMessage = error?.message.nonEmptyStringOrNull() ?: "Something went wrong."
        viewBinding?.txtInfo?.text = errorMessage
        viewBinding?.txtInfo?.isVisible = true
    }
}
