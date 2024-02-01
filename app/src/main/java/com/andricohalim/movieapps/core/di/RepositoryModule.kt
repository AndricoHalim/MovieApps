package com.andricohalim.movieapps.core.di

import com.andricohalim.movieapps.core.data.MovieRepository
import com.andricohalim.movieapps.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: MovieRepository): IMovieRepository

}