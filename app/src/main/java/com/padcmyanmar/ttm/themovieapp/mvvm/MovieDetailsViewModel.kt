package com.padcmyanmar.ttm.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import java.sql.RowId

class MovieDetailsViewModel: ViewModel(){

    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var movieDetailsLiveData: LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int){
        movieDetailsLiveData = mMovieModel.getMovieDetail(movieId = movieId.toString()){mErrorLiveData.postValue(it)}


        mMovieModel.getCreditsByMovie(movieId = movieId.toString(),
            onSuccess = {
                castLiveData.postValue(it.first ?: listOf())
                crewLiveData.postValue(it.second ?: listOf())

        }, onFailure = {
            mErrorLiveData.postValue(it)
        })
    }
}