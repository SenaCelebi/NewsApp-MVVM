package com.senacelebi.mvvmnewsapp.userinterface

import androidx.lifecycle.ViewModel
import com.senacelebi.mvvmnewsapp.repository.Repository

class NewsViewModel(
    val newsRp: Repository
) : ViewModel() {
}