package com.senacelebi.mvvmnewsapp.api

import com.senacelebi.mvvmnewsapp.model.NewsRespond
import com.senacelebi.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("v2/top-headlines")
    suspend fun getLatestNews(
        @Query("country")
        countryCode: String = "tr",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsRespond>

    @GET("v2/everything")
    suspend fun search(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsRespond>

}