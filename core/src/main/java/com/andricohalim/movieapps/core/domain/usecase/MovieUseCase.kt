package com.andricohalim.movieapps.core.domain.usecase

import com.andricohalim.movieapps.core.data.Resource
import com.andricohalim.movieapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}