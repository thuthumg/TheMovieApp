package com.padcmyanmar.ttm.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.themovieapp.mvp.views.MovieDetailsView
import java.sql.RowId

interface MovieDetailPresenter : IBasePresenter {

    fun initView(view: MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}