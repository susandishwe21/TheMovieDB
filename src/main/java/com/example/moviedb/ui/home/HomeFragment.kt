package com.example.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MovieAdapter.ClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieRecyclerView.layoutManager = GridLayoutManager(
                context, 2,
                GridLayoutManager.VERTICAL, false
        )
        movieAdapter = MovieAdapter()
        movieRecyclerView.adapter = movieAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        homeViewModel= ViewModelProvider(this)
                .get(HomeViewModel::class.java)
        homeViewModel.loadTopRateMovie()
        homeViewModel.getTopMovie().observe(viewLifecycleOwner, Observer {
            movieRecyclerView.visibility = View.VISIBLE
            loadError.visibility = View.GONE
            movieAdapter.updateTopMovie(it)
            movieAdapter.setClickListener(this)
        })

        homeViewModel.getLoadError().observe(viewLifecycleOwner,
                Observer {
                    movieRecyclerView.visibility = View.GONE
                    loadError.visibility = View.VISIBLE
                })
    }

    override fun onClick(id: Int) {
        var action = HomeFragmentDirections.actionNavHomeToNextFragment(id)
        findNavController().navigate(action)
        Log.d("ID>>>>>",id.toString())
    }
}