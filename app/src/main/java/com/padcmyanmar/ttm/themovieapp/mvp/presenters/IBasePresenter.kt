package com.padcmyanmar.ttm.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {

    fun onUiReady(owner: LifecycleOwner)
}