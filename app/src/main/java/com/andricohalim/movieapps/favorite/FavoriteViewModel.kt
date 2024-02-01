package com.andricohalim.movieapps.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}
