package com.senacelebi.mvvmnewsapp.userinterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senacelebi.mvvmnewsapp.repository.Repository

class VMProvider(
    val newsRepo: Repository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepo) as T
    }
}