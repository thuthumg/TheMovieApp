package com.padcmyanmar.ttm.themovieapp.mvi.intents

import com.padcmyanmar.ttm.themovieapp.mvi.mvibase.MVIIntent

sealed class MainIntent: MVIIntent{
    class LoadMoviesByGenreIntent(val genrePosition: Int): MainIntent()
    object LoadAllHomePageData: MainIntent()
}
