package com.senacelebi.mvvmnewsapp.userinterface.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.senacelebi.mvvmnewsapp.R
import com.senacelebi.mvvmnewsapp.util.Resource
import com.senacelebi.mvvmnewsapp.adabtor.Adaptor
import com.senacelebi.mvvmnewsapp.userinterface.MainActivity
import com.senacelebi.mvvmnewsapp.userinterface.NewsViewModel
import kotlinx.android.synthetic.main.fragment_latest_news.*

class LatestNews : Fragment(R.layout.fragment_latest_news){


   lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: Adaptor

    var TAG = "Latest News Fragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setRecycleView()

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