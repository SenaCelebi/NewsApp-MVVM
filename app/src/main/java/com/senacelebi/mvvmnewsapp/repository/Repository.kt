package com.senacelebi.mvvmnewsapp.repository

import com.senacelebi.mvvmnewsapp.api.RetrofitInstance
import com.senacelebi.mvvmnewsapp.database.ArticleDB

class Repository(
    val db: ArticleDB
) {
    suspend fun  getLatestNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getLatestNews(countryCode, pageNumber)

}