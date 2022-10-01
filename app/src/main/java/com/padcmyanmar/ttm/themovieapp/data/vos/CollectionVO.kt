package com.padcmyanmar.ttm.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class CollectionVO(

    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("posterPath")
    val posterPath: String?,
    @SerializedName("backdropPath")
    val backdropPath: String?
)
