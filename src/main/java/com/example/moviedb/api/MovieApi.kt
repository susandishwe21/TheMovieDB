package com.example.moviedb.api

import com.example.moviedb.model.Result
import com.example.moviedb.model.TopRateMovie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MovieApi {

    private val movieInterface: MovieInterface

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        movieInterface = retrofit.create(MovieInterface::class.java)
    }

    fun getTopRate(apiKey: String): Call<TopRateMovie> = movieInterface.getTopMovie(apiKey)

    fun getMovieDetail(movieID: Int, apiKey: String): Call<Result> {
        return movieInterface.getMovieDetail(movieID, apiKey)
    }
}