package com.andricohalim.movieapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}
