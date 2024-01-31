package com.andricohalim.movieapps.core.data.source.remote.network

import com.andricohalim.movieapps.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("3/discover/movie")
    fun getMovies(): Call<ListMovieResponse>
}