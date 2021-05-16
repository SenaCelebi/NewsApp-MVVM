package com.senacelebi.mvvmnewsapp.userinterface

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

    init {
        getLatestNews("tr")
    }

    fun getLatestNews(countryCode: String) = viewModelScope.launch {
        latestNews.postValue(Resource.Loading())
        val res = newsRp.getLatestNews(countryCode, latestNewsPage)
        latestNews.postValue((handleLatestNewsResponse(res)))


    }

    private fun handleLatestNewsResponse(response: Response<NewsRespond>) : Resource<NewsRespond>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}