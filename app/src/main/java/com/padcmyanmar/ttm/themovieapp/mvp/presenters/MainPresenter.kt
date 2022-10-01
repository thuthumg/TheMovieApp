package com.padcmyanmar.ttm.themovieapp.mvp.presenters

import com.padcmyanmar.ttm.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition:Int)

}