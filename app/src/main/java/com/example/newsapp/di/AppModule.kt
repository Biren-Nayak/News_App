package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.database.ArticlesDao
import com.example.newsapp.database.NewsListDatabase
import com.example.newsapp.network.NewsApi
import com.example.newsapp.repository.ArticleRepository
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun myRepoImpl(articlesDao: ArticlesDao, newsApi: NewsApi) =
        ArticleRepository(articlesDao, newsApi)


    @Provides
    @Singleton
    fun myDaoImpl(database: NewsListDatabase) = database.articlesDao()


    @Provides
    @Singleton
    fun myDatabaseImpl(application: Application) = Room
        .databaseBuilder(application, NewsListDatabase::class.java, Constants.TABLE_NAME)
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun myApiImpl(): NewsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(NewsApi::class.java)

}