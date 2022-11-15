package com.example.newsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newsapp.database.SourceTypeConverter
import com.example.newsapp.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
@TypeConverters(SourceTypeConverter::class)
data class Article(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(defaultValue = "1") val id: Int,
    @ColumnInfo(defaultValue = "") val author: String,
    @ColumnInfo(defaultValue = "") val content: String,
    @ColumnInfo(defaultValue = "")val description: String,
    @ColumnInfo(defaultValue = "") val publishedAt: String,
    @ColumnInfo(defaultValue = "") val source: Source,
    @ColumnInfo(defaultValue = "") val title: String,
    @ColumnInfo(defaultValue = "") val url: String,
    @ColumnInfo(defaultValue = "") val urlToImage: String
)