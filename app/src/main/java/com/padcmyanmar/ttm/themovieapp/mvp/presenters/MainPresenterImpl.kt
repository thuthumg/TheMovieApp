package com.padcmyanmar.ttm.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {


    //View
    var mView: MainView? = null

    //Model
    private val mMovieModel:MovieModel = MovieModelImpl

    //States
    private var mGenres: List<GenreVO>? = listOf()

    override fun onUiReady(owner: LifecycleOwner) {
        //Now Playing
        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        //Popular Movies
        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }


        //Top Rated Movies
        mMovieModel.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        //Genre and Get Movies For First Genre
        mMovieModel.getGenres(onSuccess = {
            mGenres = it
            mView?.showGenres(it)
            it.firstOrNull()?.id?.let{ firstGenreId ->
                onTapGenre(firstGenreId)

            }
        },
        onFailure = {
            mView?.showError(it)
        })


        //Actors
        mMovieModel.getActors(onSuccess = {
            mView?.showActors(it)
        },
        onFailure = {
            mView?.showError(it)
        })
    }

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {
       mGenres?.getOrNull(genrePosition)?.id?.let { genreId->
           mMovieModel.getMoviesByGenre(
               genreId = genreId.toString(),
               onSuccess = {
                   mView?.showMoviesByGenre(it)
               },
               onFailure = {
                   mView?.showError(it)
               }
           )

       }
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }
}