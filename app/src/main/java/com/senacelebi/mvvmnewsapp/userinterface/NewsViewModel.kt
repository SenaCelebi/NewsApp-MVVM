package com.senacelebi.mvvmnewsapp.userinterface

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senacelebi.mvvmnewsapp.model.NewsRespond
import com.senacelebi.mvvmnewsapp.repository.Repository
import com.senacelebi.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRp: Repository
) : ViewModel() {

    val latestNews: MutableLiveData<Resource<NewsRespond>> = MutableLiveData()
    var latestNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsRespond>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getLatestNews("tr")
    }

    fun getLatestNews(countryCode: String) = viewModelScope.launch {
        latestNews.postValue(Resource.Loading())
        val res = newsRp.getLatestNews(countryCode, latestNewsPage)
        latestNews.postValue((handleLatestNewsResponse(res)))


    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRp.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleLatestNewsResponse(response: Response<NewsRespond>) : Resource<NewsRespond>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsRespond>) : Resource<NewsRespond>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}