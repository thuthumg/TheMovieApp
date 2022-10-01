package com.padcmyanmar.ttm.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_showcase.view.*

class ShowcaseViewHolder(itemView: View,private val mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null
    init {

        itemView.setOnClickListener {
            mMovieVO?.let {movie ->
                mDelegate.onTapMovieFromShowcase(movie.id)

            }
        }
    }

    fun bindData(movieVO: MovieVO){

        mMovieVO = movieVO

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movieVO.posterPath}")
            .into(itemView.ivShowCase)
        itemView.tvShowCaseMovieName.text = movieVO.title
        itemView.tvShowCaseMovieDate.text = movieVO.releaseDate
    }
}