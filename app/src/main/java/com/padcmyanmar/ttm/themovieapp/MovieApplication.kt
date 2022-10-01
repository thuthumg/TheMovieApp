package com.padcmyanmar.ttm.themovieapp

import android.app.Application
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)

    }
}