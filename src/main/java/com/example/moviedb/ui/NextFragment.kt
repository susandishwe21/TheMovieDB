package com.example.moviedb.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviedb.R
import com.example.moviedb.ui.home.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.home_top_movie_item.*

class NextFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_next, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var messageArgs = arguments.let { NextFragmentArgs.fromBundle(it!!) }
       observeViewModel(messageArgs.movieID)

    }

    private fun  observeViewModel(movieID:Int){
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.loadMovieDetail(movieID)
        homeViewModel.getMovieDetail().observe(
                viewLifecycleOwner, Observer {
           txtOverview.text = it.overview
            txtReleaseDate.text = it.release_date
            txtVoteAverage.text = it.vote_average.toString()

            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageMovieDetail)

            Log.d("Detail",homeViewModel.getMovieDetail().toString())
        }
        )
    }

    override fun onResume() {
        super.onResume()
//        var data = arguments.let { NextFragmentArgs.fromBundle(it!!)  }
//        var resultData = data.movieID
//        Log.d("Movie ID",resultData.toString())
//        homeViewModel.loadMovieDetail(resultData)
    }

}