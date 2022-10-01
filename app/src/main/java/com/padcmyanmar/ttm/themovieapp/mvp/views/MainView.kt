package com.padcmyanmar.ttm.themovieapp.mvp.views

import android.graphics.Movie
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO

interface MainView: BaseView {

    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenre(moviesByGenre: List<MovieVO>)
    fun showActors(actors: List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieId: Int)
}