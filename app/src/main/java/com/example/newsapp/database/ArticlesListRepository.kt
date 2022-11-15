package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ArticlesListRepository(private val  database: NewsListDatabase) {

    suspend fun refreshArticles() {
        withContext(Dispatchers.IO){
            val articles = NewsApi.retrofitService.getNews().articles
            database.articlesDao().insertArticle(*articles.toTypedArray())
        }
    }

    val articles: LiveData<List<Article>> = liveData { NewsApi.retrofitService.getNews().articles }
}