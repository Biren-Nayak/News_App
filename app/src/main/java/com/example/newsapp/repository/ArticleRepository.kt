package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.newsapp.database.NewsListDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApiService

class ArticleRepository(private val database: NewsListDatabase) {

    val articles: LiveData<List<Article>> = database.articlesDao().getAllArticles().asLiveData()

    suspend fun refreshVideos(value: String) {
        val articles =  if (value.isBlank())
            NewsApiService.retrofitService.getNews().articles
            else
                NewsApiService.retrofitService.getNews(value).articles
        database.articlesDao().apply{
            clear()
            insertArticle(*articles.toTypedArray())
        }
    }
}