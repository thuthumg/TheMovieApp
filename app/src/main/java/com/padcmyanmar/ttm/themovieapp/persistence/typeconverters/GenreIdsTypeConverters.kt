package com.padcmyanmar.ttm.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themovieapp.data.vos.CollectionVO

class GenreIdsTypeConverters {

    @TypeConverter
    fun toString(genreListId: List<Int>?): String {
        return Gson().toJson(genreListId)
    }

    @TypeConverter
    fun toGenreIds(genreListIdJsonStr: String): List<Int>? {
        var genreIdsVO  = object : TypeToken<List<Int>?>(){}.type

        return Gson().fromJson(genreListIdJsonStr,genreIdsVO)
    }

}