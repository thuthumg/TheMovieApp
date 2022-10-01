package com.padcmyanmar.ttm.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themovieapp.data.vos.SpokenLanguagesVO

class SpokenLanguageTypeConverter {

    @TypeConverter
    fun toString(spokenLanguages: List<SpokenLanguagesVO>?):String{
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguage(spokenLanguagesJsonStr: String):List<SpokenLanguagesVO>?{
       var spokenLanguageListType = object : TypeToken<List<SpokenLanguagesVO>?>(){}.type
        return Gson().fromJson(spokenLanguagesJsonStr,spokenLanguageListType)
    }
}