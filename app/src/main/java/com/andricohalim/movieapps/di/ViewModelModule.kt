package com.andricohalim.movieapps.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.movieapps.core.ui.ViewModelFactory
import com.andricohalim.movieapps.detail.DetailMovieViewModel
import com.andricohalim.movieapps.favorite.FavoriteViewModel
import com.andricohalim.movieapps.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailTourismViewModel(viewModel: DetailMovieViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}