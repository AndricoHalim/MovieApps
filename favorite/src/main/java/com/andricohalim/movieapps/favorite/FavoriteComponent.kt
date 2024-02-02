package com.andricohalim.movieapps.favorite

import android.content.Context
import com.andricohalim.movieapps.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependecies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}