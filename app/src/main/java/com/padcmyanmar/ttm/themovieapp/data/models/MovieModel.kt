package com.padcmyanmar.ttm.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {

    fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>?


    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMovie(
        query: String
    ): Observable<List<MovieVO>>?
}