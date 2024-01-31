package com.andricohalim.movieapps.detail

import androidx.lifecycle.ViewModel
import com.andricohalim.movieapps.core.domain.model.Movie
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}