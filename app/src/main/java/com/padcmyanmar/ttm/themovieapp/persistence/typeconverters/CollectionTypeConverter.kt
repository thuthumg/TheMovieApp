package com.padcmyanmar.ttm.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {

    @TypeConverter
    fun toString(collection: CollectionVO?): String {
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(collectionListJsonStr: String): CollectionVO? {
        var collectionVOType  = object : TypeToken<CollectionVO?>(){}.type

        return Gson().fromJson(collectionListJsonStr,collectionVOType)
    }

}