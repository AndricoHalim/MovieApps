package com.andricohalim.movieapps.di

import com.andricohalim.movieapps.core.di.CoreComponent
import com.andricohalim.movieapps.detail.DetailMovieActivity
import com.andricohalim.movieapps.favorite.FavoriteFragment
import com.andricohalim.movieapps.home.HomeFragment
import com.andricohalim.movieapps.search.SearchFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailMovieActivity)
    fun inject(fragment: SearchFragment)
}