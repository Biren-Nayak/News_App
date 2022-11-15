package com.example.newsapp.database

import androidx.room.TypeConverter
import com.example.newsapp.models.Source
import org.json.JSONObject

class SourceTypeConverter {
    private val id = "id"
    private val name = "name"
    @TypeConverter
    fun fromSource(source: Source): String{
        return JSONObject().apply {
            put(id, source.id ?: "")
            put(name, source.name ?: "")
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Source{
        val json = JSONObject(source)
        return json.run { Source( getString(id), getString(name)) }
    }

}