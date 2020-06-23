package com.example.moviedb.api

import android.telecom.Call
import com.example.moviedb.model.Result
import com.example.moviedb.model.TopRateMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("top_rated")
    fun getTopMovie(
        @Query("api_key") apiKey : String
    ):retrofit2.Call<TopRateMovie>

    @GET("{id}")
    fun getMovieDetail(
            @Path("id") movieID : Int,
            @Query("api_key") apiKey : String
    ):retrofit2.Call<Result>
}