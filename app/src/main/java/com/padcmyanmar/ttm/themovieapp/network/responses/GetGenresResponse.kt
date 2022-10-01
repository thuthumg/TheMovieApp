package com.padcmyanmar.ttm.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO

data class GetGenresResponse(

    @SerializedName("genres")
    val genres: List<GenreVO>?
)