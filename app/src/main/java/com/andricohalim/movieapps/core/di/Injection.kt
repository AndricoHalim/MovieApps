package com.andricohalim.movieapps.core.di

import android.content.Context
import com.andricohalim.movieapps.core.data.MovieRepository
import com.andricohalim.movieapps.core.data.source.local.LocalDataSource
import com.andricohalim.movieapps.core.data.source.local.room.MovieDatabase
import com.andricohalim.movieapps.core.data.source.remote.RemoteDataSource
import com.andricohalim.movieapps.core.data.source.remote.network.ApiConfig
import com.andricohalim.movieapps.core.domain.repository.IMovieRepository
import com.andricohalim.movieapps.core.domain.usecase.MovieInteractor
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import com.andricohalim.movieapps.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}
