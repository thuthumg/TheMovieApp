package com.padcmyanmar.ttm.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.themovieapp.data.vos.ProductionCountriesVO

class ProductionCountryTypeConverter {


    @TypeConverter
    fun toString(productionCountries: List<ProductionCountriesVO>?): String {
        return Gson().toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountries(productionCountriesJsonStr: String): List<ProductionCountriesVO>?{
        var productionCountriesListType  = object : TypeToken<List<ProductionCountriesVO>?>(){}.type

        return Gson().fromJson(productionCountriesJsonStr,productionCountriesListType)
    }
}