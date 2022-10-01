package com.padcmyanmar.ttm.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.ProductionCompaniesVO

class ProductionCompanyTypeConverter {

    @TypeConverter
    fun toString(productionCompanies: List<ProductionCompaniesVO>?): String {
        return Gson().toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompaniesJsonStr: String): List<ProductionCompaniesVO>? {
        var productionCompaniesListType  = object : TypeToken<List<ProductionCompaniesVO>?>(){}.type

        return Gson().fromJson(productionCompaniesJsonStr,productionCompaniesListType)
    }
}