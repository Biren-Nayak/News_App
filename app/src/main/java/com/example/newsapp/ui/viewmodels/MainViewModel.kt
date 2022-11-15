package com.example.newsapp.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.newsapp.database.NewsListDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApi
import com.example.newsapp.repository.ArticleRepository
import com.example.newsapp.utils.FetchStatus
import com.example.newsapp.utils.FetchStatus.*
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : ViewModel() {

    private val articlesRepository = ArticleRepository(NewsListDatabase.getDatabase(application))

    private val _status = MutableLiveData<FetchStatus>()
    val status: LiveData<FetchStatus> = _status


    val listData = articlesRepository.articles

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article> = _selectedArticle



    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            _status.value = LOADING
            try {
                articlesRepository.refreshVideos()
                _status.value = SUCCESS
            }catch (e: Exception){
                _status.value = ERROR
                Log.e("TAG", e.message!!)
            }
        }
    }

    fun onArticleClicked(article: Article){
        _selectedArticle.value = article
    }

}

class MainViewModelFactory(val application: Application): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Cannot create viewModel")
    }
}