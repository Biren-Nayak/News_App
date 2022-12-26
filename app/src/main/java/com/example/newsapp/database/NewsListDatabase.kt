package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article

@Database(entities = [Article::class], version = 5)
abstract class NewsListDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao

}