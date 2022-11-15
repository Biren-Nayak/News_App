package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.database.SourceTypeConverter
import com.example.newsapp.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
@TypeConverters(SourceTypeConverter::class)
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)