package com.senacelebi.mvvmnewsapp.model

import com.senacelebi.mvvmnewsapp.model.Article

data class NewsRespond(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)