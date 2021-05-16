package com.senacelebi.mvvmnewsapp.database

import androidx.room.TypeConverter
import com.senacelebi.mvvmnewsapp.model.Source


class Converter {
    @TypeConverter
    fun convertFromSource(source: Source): String {
        return source.name
    }

    @TypeConverter

    fun toSource(name: String): Source {
        return  Source(name, name)
    }
}