package com.padcmyanmar.ttm.themovieapp.data.models


import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.themovieapp.data.vos.*

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {

    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        //Network
        mTheMovieApi.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                    mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
                    // onSuccess(it.results?: listOf())
                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )

        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        mTheMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movie -> movie.type = POPULAR }
                    mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
                    //  onSuccess( it.results?: listOf())

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )

        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR);
    }

    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        mTheMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movie -> movie.type = TOP_RATED }
                    mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
                    //  onSuccess( it.results?: listOf())

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED);
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        //Network
        mTheMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(it.genres ?: listOf())

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        //Network
        mTheMovieApi.getMoviesByGenre(
            genreId = genreId
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(it.results ?: listOf())

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
    }


    //popular actors
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        //Network
        mTheMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(it.results ?: listOf())

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
    }

    override fun getMovieDetail(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {

        //Network
        mTheMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val movieFromDatabaseToSync = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(
                        movieId = movieId.toInt()
                    )
                    it.type = movieFromDatabaseToSync?.type

                    mMovieDatabase?.movieDao()?.insertSingleMovies(it)

                    // onSuccess(it)

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )

        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Network
        mTheMovieApi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))

                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mTheMovieApi.searchMovie(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }
}