package com.padcmyanmar.ttm.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.ttm.themovieapp.R
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO
import com.padcmyanmar.ttm.themovieapp.mvp.presenters.IBasePresenter
import com.padcmyanmar.ttm.themovieapp.mvp.presenters.MovieDetailPresenter
import com.padcmyanmar.ttm.themovieapp.mvp.presenters.MovieDetailsPresenterImpl
import com.padcmyanmar.ttm.themovieapp.mvp.views.MovieDetailsView
import com.padcmyanmar.ttm.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.ttm.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.view_holder_movie.*

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {


    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {


            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)

            return intent
        }
    }


    //View Pods
    private lateinit var actorsViewPod: ActorListViewPod
    private lateinit var creatorsViewPod: ActorListViewPod

    //Presenter
    private lateinit var mPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpPresenter()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setUpViewPods()
        setUpListeners()
        getMovieDetailIntentParamAndRequestData()


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MovieDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun getMovieDetailIntentParamAndRequestData() {

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        // Snackbar.make(window.decorView,"EXTRA MOVIE ID = $movieId",Snackbar.LENGTH_SHORT).show()
        movieId?.let {
            mPresenter.onUiReadyInMovieDetails(this, movieId = movieId)
        }
    }


    private fun bindData(movieVO: MovieVO) {
        collapsingToolbarLayoutTitle.title = movieVO.title ?: ""
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movieVO.posterPath}")
            .into(ivMovieDetails)
        tvMovieDetailName.text = movieVO.title ?: ""
        tvMovieReleaseYear.text = movieVO.releaseDate?.substring(0, 4)
        tvRating.text = movieVO.voteAverage?.toString() ?: ""
        movieVO.voteCount?.let { voteCount ->
            tvNumberOfVotes.text = "$voteCount VOTES"
        }
        rbRatingMovieDetails.rating = movieVO.getRatingBasedOnFiveStars()

        bindGenres(movieVO, movieVO.genres ?: listOf())

        tvOverview.text = movieVO.overview ?: ""
        tvOriginalTitle.text = movieVO.title ?: ""
        tvType.text = movieVO.getGenresAsCommonSeparatedString()
        tvProduction.text = movieVO.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movieVO.releaseDate ?: ""
        tvDescription.text = movieVO.overview ?: ""


    }

    private fun bindGenres(movieVO: MovieVO, genres: List<GenreVO>) {

        movieVO.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }

    }

    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
        actorsViewPod.setData(cast)
        creatorsViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_actors)
        )

    }
}