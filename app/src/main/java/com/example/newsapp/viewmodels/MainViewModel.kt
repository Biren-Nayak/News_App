package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.repository.ArticleRepository
import com.example.newsapp.utils.Constants.CATEGORY_BUSINESS
import com.example.newsapp.utils.Constants.FetchStatus
import com.example.newsapp.utils.Constants.FetchStatus.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val articlesRepository: ArticleRepository) :
    ViewModel() {

    private val _status = MutableLiveData<FetchStatus>()
    val status: LiveData<FetchStatus> = _status

    val listData = articlesRepository.articles

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article> = _selectedArticle


    init {
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository(category: String = CATEGORY_BUSINESS) {
        viewModelScope.launch {
            _status.value = LOADING
            try {
                articlesRepository.refreshVideos(category)
                _status.value = SUCCESS
            } catch (e: Exception) {
                _status.value = ERROR
                Log.e("TAG", e.message!!)
            }
        }
    }

    fun onArticleClicked(article: Article) {
        _selectedArticle.value = article
    }

}

