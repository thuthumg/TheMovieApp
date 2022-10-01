package com.padcmyanmar.ttm.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO

data class GetActorResponse(

    @SerializedName("results")
    val results: List<ActorVO>
)
