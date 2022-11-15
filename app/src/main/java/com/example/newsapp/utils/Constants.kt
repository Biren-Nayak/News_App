package com.example.newsapp.utils

object Constants{
    const val BASE_URL = "https://saurav.tech/NewsAPI/top-headlines/category/"
    const val CATEGORY_HEALTH = "health"
    const val Country_DOMAIN = "in"
    const val FORMAT = "json"
    const val DATA = "data"
    const val IMG_URL = "imgUrl"
    const val FETCH_STATUS = "fetchStatus"
    const val TABLE_NAME = "news"
    const val HTTPS = "https"

    enum class FetchStatus { LOADING, SUCCESS, ERROR }
}