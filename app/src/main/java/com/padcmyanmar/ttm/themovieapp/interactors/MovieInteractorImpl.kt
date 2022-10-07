package com.padcmyanmar.ttm.themovieapp.interactors

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.data.vos.*

import io.reactivex.rxjava3.core.Observable


object MovieInteractorImpl : MovieInteractor{

    //Model

    private val mMovieModel : MovieModel = MovieModelImpl
    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
      return  mMovieModel.getPopularMovies (onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return  mMovieModel.getTopRatedMovies (onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        return  mMovieModel.getGenres (onSuccess,onFailure)
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return  mMovieModel.getMoviesByGenre (genreId,onSuccess,onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
      return mMovieModel.getActors(onSuccess,onFailure)
    }

    override fun getMovieDetail(movieId: String, onFailure: (String) -> Unit): LiveData<MovieVO?>? {
        return mMovieModel.getMovieDetail(movieId,onFailure)
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getCreditsByMovie(movieId,onSuccess,onFailure)
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>>? {
        return mMovieModel.searchMovie(query)
    }


}