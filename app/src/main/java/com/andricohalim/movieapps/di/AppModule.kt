package com.andricohalim.movieapps.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.andricohalim.movieapps.core.domain.usecase.MovieInteractor
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import com.andricohalim.movieapps.settings.dataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    companion object {
        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return context.dataStore
        }
    }
}

