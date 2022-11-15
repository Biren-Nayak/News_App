package com.example.newsapp.network

import com.example.newsapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiService{

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}