package com.padcmyanmar.ttm.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.ttm.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.ttm.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int){
    startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
}

fun Activity.navigateToMovieSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}