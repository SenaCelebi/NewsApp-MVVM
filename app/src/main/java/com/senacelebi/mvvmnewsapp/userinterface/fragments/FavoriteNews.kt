package com.senacelebi.mvvmnewsapp.userinterface.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.senacelebi.mvvmnewsapp.R
import com.senacelebi.mvvmnewsapp.userinterface.MainActivity
import com.senacelebi.mvvmnewsapp.userinterface.NewsViewModel

class FavoriteNews : Fragment(R.layout.fragment_favorite_news){

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}