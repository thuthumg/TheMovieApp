package com.padcmyanmar.ttm.themovieapp.activities

//import com.padcmyanmar.ttm.themovieapp.network.dataagents.OkHttpDataAgentImpl

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.themovieapp.R

import com.padcmyanmar.ttm.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.ttm.themovieapp.adapters.ShowcaseAdapter
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModel
import com.padcmyanmar.ttm.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.ttm.themovieapp.data.vos.GenreVO
import com.padcmyanmar.ttm.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.ttm.themovieapp.mvi.intents.MainIntent
import com.padcmyanmar.ttm.themovieapp.mvi.mvibase.MVIView
import com.padcmyanmar.ttm.themovieapp.mvi.states.MainState
import com.padcmyanmar.ttm.themovieapp.mvi.viewmodels.MainViewModel
import com.padcmyanmar.ttm.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.ttm.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*


class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate, MVIView<MainState>{

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    //Data
    private var mGenres: List<GenreVO>? = null

    //Model
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set Up View Model
        setUpViewModel()


        // App Bar Leading Icon
        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        //setUpGenreTabLayout()
        setUpShowCaseRecyclerView()

        setUpListeners()


        //Set Initial Intents
        setInitialIntents()
        observeState()

    }

    private fun setInitialIntents() {
       mViewModel.processIntent(MainIntent.LoadAllHomePageData,this)
    }

    private fun observeState(){
        mViewModel.state.observe(this, this::render)
    }
    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
    private fun showError(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorsHomeScreen as ActorListViewPod
    }

    private fun setUpShowCaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowcases.adapter = mShowcaseAdapter
        rvShowcases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpListeners() {
        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                mViewModel.processIntent(
                    MainIntent.LoadMoviesByGenreIntent(
                        tab?.position?: 0),
                    this@MainActivity
                    )

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

        mBannerAdapter = BannerAdapter(this)
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

    override fun onTapMovieFromBanner(movieId: Int) {
        // Snackbar.make(window.decorView,"Tapped Movie From Banner = $movieId",Snackbar.LENGTH_LONG).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        //   Snackbar.make(window.decorView,"Tapped Movie From Showcase = $movieId",Snackbar.LENGTH_LONG).show()
        startActivity(MovieDetailsActivity.newIntent(this, movieId))

    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))

        //   Snackbar.make(window.decorView,"Tapped Movie From Best Popular Movies or Movies By Genre = $movieId",Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId)
        {
           R.id.btnSearch ->{
               this.startActivity(MovieSearchActivity.newIntent(this))

               true
           }else -> {
            super.onOptionsItemSelected(item)
           }
        }


    }

    override fun render(state: MainState) {
       if(state.errorMessage.isNotEmpty()){
           showError(state.errorMessage)
       }

        mBannerAdapter.setNewData(state.nowPlayingMovies)
        mBestPopularMovieListViewPod.setData(state.popularMovies)
        mShowcaseAdapter.setNewData(state.topRatedMovies)
        setUpGenreTabLayout(state.genres)
        mMoviesByGenreViewPod.setData(state.moviesByGenre)
        mActorListViewPod.setData(state.actors)
    }
}