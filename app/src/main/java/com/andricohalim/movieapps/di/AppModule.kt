package com.andricohalim.movieapps.di

import com.andricohalim.movieapps.core.domain.usecase.MovieInteractor
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

}