package com.example.newsapp.network

import com.example.newsapp.models.NewsResult
import com.example.newsapp.utils.Constants.CATEGORY_BUSINESS
import com.example.newsapp.utils.Constants.Country_DOMAIN
import com.example.newsapp.utils.Constants.FORMAT
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("{category}/$Country_DOMAIN.$FORMAT")
    suspend fun getNews(@Path("category") category: String = CATEGORY_BUSINESS): NewsResult
}

