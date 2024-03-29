package com.padcmyanmar.ttm.themovieapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovies(movie: MovieVO?)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieVO>>?

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId:Int): LiveData<MovieVO?>?

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieByIdOneTime(movieId:Int): MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieByType(type:String): LiveData<List<MovieVO>>?

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}