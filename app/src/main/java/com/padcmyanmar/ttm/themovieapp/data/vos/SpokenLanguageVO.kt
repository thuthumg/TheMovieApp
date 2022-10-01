package com.padcmyanmar.ttm.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesVO(
    @SerializedName("name")
    val name: String?,

    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso6391: String?

)