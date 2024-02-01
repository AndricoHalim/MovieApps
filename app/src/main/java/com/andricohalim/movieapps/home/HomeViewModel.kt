package com.andricohalim.movieapps.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}