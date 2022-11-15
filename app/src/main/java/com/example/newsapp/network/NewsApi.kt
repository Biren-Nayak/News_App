package com.example.newsapp.network

import com.example.newsapp.models.NewsResult
import com.example.newsapp.utils.Constants.CATEGORY_HEALTH
import com.example.newsapp.utils.Constants.Country_DOMAIN
import com.example.newsapp.utils.Constants.FORMAT
import retrofit2.http.GET

interface NewsApi {
    @GET("$CATEGORY_HEALTH/$Country_DOMAIN.$FORMAT")
    suspend fun getNews(): NewsResult
}

