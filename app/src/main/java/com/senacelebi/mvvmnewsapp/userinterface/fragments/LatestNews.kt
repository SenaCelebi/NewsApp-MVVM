package com.senacelebi.mvvmnewsapp.userinterface.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.senacelebi.mvvmnewsapp.R
import com.senacelebi.mvvmnewsapp.util.Resource
import com.senacelebi.mvvmnewsapp.adabtor.Adaptor
import com.senacelebi.mvvmnewsapp.userinterface.MainActivity
import com.senacelebi.mvvmnewsapp.userinterface.NewsViewModel
import com.senacelebi.mvvmnewsapp.util.Constants.Companion.SEARCH_NEWS_DELAY
import kotlinx.android.synthetic.main.fragment_latest_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LatestNews : Fragment(R.layout.fragment_latest_news){


   lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: Adaptor

    var TAG = "Latest News Fragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setRecycleView()

        var job: Job? = null
        etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }

            }
        }

        viewModel.latestNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }

                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let{message ->
                        Log.e(TAG, "An error is happened: $message")

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()

                }
            }

        })


        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }

                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let{message ->
                        Log.e(TAG, "An error is happened: $message")

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()

                }
            }

        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private  fun setRecycleView(){
        newsAdapter = Adaptor()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}