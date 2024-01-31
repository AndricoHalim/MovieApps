package com.andricohalim.movieapps.core.domain.usecase

import androidx.lifecycle.LiveData
import com.andricohalim.movieapps.core.data.Resource
import com.andricohalim.movieapps.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
    fun getFavoriteMovie(): LiveData<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}