package com.padcmyanmar.ttm.themovieapp.mvi.mvibase

interface MVIView<S : MVIState> {
    fun render(state: S)
}