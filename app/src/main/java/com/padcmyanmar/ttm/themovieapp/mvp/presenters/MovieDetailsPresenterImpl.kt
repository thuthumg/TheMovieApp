package com.padcmyanmar.ttm.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl : ViewModel(), MovieDetailPresenter {

    //View
    private var mView: MovieDetailsView? = null

    //Model
    private val mMovieModel = MovieModelImpl


    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        // Movie Details
        mMovieModel.getMovieDetail(movieId = movieId.toString()) {
            mView?.showError(it)
        }?.observe(owner) {
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        //Credits
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(),
            onSuccess = {

                mView?.showCreditsByMovie(cast = it.first, crew = it.second)
            }, onFailure = {
                mView?.showError(it)
            })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}