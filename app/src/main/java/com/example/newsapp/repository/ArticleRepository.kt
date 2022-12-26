package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.newsapp.database.ArticlesDao
import com.example.newsapp.models.Article
import com.example.newsapp.network.NewsApi
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val newsApi: NewsApi,
) {

    val articles: LiveData<List<Article>> = articlesDao.getAllArticles().asLiveData()

    suspend fun refreshVideos(value: String) {
        val articles = if (value.isBlank())
            newsApi.getNews().articles
        else
            newsApi.getNews(value).articles
        articlesDao.apply {
            clear()
            insertArticle(*articles.toTypedArray())
        }
    }
}