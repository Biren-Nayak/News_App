package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.newsapp.database.NewsListDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRepository(private val database: NewsListDatabase) {

    val articles: LiveData<List<Article>> = database.articlesDao().getAllArticles().asLiveData()

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val articles =  NewsApi.retrofitService.getNews().articles
            database.articlesDao().insertArticle(*articles.toTypedArray())
        }
    }
}