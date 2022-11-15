package com.example.newsapp.models

data class NewsResult (
    val articles: List<Article>,
    val status: String,
    val totalResult: Int
)