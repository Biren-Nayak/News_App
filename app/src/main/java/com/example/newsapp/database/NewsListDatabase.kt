package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.utils.TABLE_NAME

@Database(entities = [Article::class], version = 5)
abstract class NewsListDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao

    companion object {
        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            buildRoomDB(context).also { INSTANCE = it }
        }

        @Volatile
        private var INSTANCE: NewsListDatabase? = null

        private fun buildRoomDB(context: Context) = Room
            .databaseBuilder(context, NewsListDatabase::class.java, TABLE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}