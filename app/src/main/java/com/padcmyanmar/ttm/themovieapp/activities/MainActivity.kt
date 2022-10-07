package com.padcmyanmar.ttm.themovieapp.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.themovieapp.R

import com.padcmyanmar.ttm.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.ttm.themovieapp.adapters.ShowcaseAdapter

import com.padcmyanmar.ttm.themovieapp.data.vos.ActorVO
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.data.vos.MovieVO

import com.padcmyanmar.ttm.themovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.themovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.ttm.themovieapp.mvp.views.MainView
import com.padcmyanmar.ttm.themovieapp.routers.navigateToMovieDetailsActivity
import com.padcmyanmar.ttm.themovieapp.routers.navigateToMovieSearchActivity
import com.padcmyanmar.ttm.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.ttm.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {


    private lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    //View Pods
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod

    //Presenter
    private lateinit var mPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        // App Bar Leading Icon
        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()
        setUpListeners()

        mPresenter.onUiReady(this)


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMoviesByGenreViewPod.setData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int) {
      //  startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
        navigateToMovieDetailsActivity(movieId = movieId)
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_LONG).show()
    }


    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpActorsHomeScreen as ActorListViewPod
    }

    private fun setUpShowCaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(mPresenter)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpListeners() {
        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }

        }
    }

    private fun setUpBannerViewPager() {

        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // to show leading icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // add leading icon


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnSearch -> {
               // this.startActivity(MovieSearchActivity.newIntent(this))
                navigateToMovieSearchActivity()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }


    }
}