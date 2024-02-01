package com.andricohalim.movieapps.core.domain.repository

import com.andricohalim.movieapps.core.data.Resource
import com.andricohalim.movieapps.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}
