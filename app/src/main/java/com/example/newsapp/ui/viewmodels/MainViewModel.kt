package com.example.newsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApi
import com.example.newsapp.utils.FetchStatus
import com.example.newsapp.utils.FetchStatus.*
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {


    private val _status = MutableLiveData<FetchStatus>()
    val status: LiveData<FetchStatus> = _status


    private val _listData = MutableLiveData<List<Article>>()
    val listData: LiveData<List<Article>> = _listData

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article> = _selectedArticle



    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            _status.value = LOADING
            try {
                _listData.value = NewsApi.retrofitService.getNews().articles
                _status.value = SUCCESS

                Log.e("TAG", _listData.value?.first()?.title.toString())
            }catch (e: Exception){
                _status.value = ERROR
                _listData.value = listOf()
                Log.e("TAG", e.message!!)
            }
        }
    }

    fun onArticleClicked(article: Article){
        _selectedArticle.value = article
    }

}
