package com.andricohalim.movieapps.core.di

import com.andricohalim.movieapps.core.data.MovieRepository
import com.andricohalim.movieapps.core.domain.repository.IMovieRepository
import com.andricohalim.movieapps.core.domain.usecase.SettingUseCase
import com.andricohalim.movieapps.core.utils.SettingPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: MovieRepository): IMovieRepository


    @Binds
    abstract fun provideSettingUseCase(settingPreferences: SettingPreferences): SettingUseCase
}