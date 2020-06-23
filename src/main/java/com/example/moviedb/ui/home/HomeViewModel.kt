package com.example.moviedb.ui.home

import android.net.DnsResolver
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.api.MovieApi
import com.example.moviedb.model.Result
import com.example.moviedb.model.TopRateMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var topMovie: MutableLiveData<List<Result>> = MutableLiveData()
    var movieDetail :MutableLiveData<Result> =MutableLiveData()
    var loadError : MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getTopMovie() : LiveData<List<Result>> = topMovie
    fun getMovieDetail(): LiveData<Result> = movieDetail
    fun getLoadError() : LiveData<Boolean> = loadError

    var movieApi = MovieApi()

    fun loadTopRateMovie(){
        loading.value = true
        var apiCallTopRateMovie = movieApi.getTopRate("492d89b92b96921e521f24bfe0a61d86")
        apiCallTopRateMovie.enqueue(object : Callback<TopRateMovie>{
            override fun onFailure(call: Call<TopRateMovie>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<TopRateMovie>, response: Response<TopRateMovie>) {
                var topRateMovieList = response.body()?.results?: emptyList()
                topMovie.value =topRateMovieList
            }

        })
    }
    fun loadMovieDetail(id : Int){
        loading.value = true
        var apiCallMovieDetail = movieApi.getMovieDetail(id,"492d89b92b96921e521f24bfe0a61d86")
        apiCallMovieDetail.enqueue(object : Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                var movieDetailList = response.body()
                Log.d("Detail", movieDetailList.toString())
                movieDetail.value = movieDetailList
            }

        })

    }
}