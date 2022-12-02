package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Constants.TABLE_NAME
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticlesDao{
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(vararg article: Article)

}
