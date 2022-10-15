package com.padcmyanmar.ttm.themovieapp.mvi.states

import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themovieapp.mvi.mvibase.MVIState

data class MainState(
    val errorMessage: String = "",
    val nowPlayingMovies: List<MovieVO>,
    val popularMovies: List<MovieVO>,
    val topRatedMovies: List<MovieVO>,
    val genres: List<GenreVO>,
    val moviesByGenre: List<MovieVO>,
    val actors: List<ActorVO>
): MVIState{
    companion object{
        fun idle(): MainState = MainState(
            errorMessage = "",
            nowPlayingMovies = listOf(),
            popularMovies = listOf(),
            topRatedMovies = listOf(),
            genres = listOf(),
            moviesByGenre = listOf(),
            actors = listOf()
        )
    }
}
