package com.padcmyanmar.ttm.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCountriesVO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("iso_3166_1")
    val iso31661: String?

)
