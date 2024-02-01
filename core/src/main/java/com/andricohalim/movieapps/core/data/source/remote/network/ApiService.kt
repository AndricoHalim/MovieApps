package com.andricohalim.movieapps.core.data.source.remote.network

import com.andricohalim.movieapps.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/now_playing")
    suspend fun getMovies(): ListMovieResponse
}