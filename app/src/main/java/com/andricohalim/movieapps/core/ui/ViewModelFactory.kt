package com.andricohalim.movieapps.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.movieapps.core.domain.usecase.MovieUseCase
import com.andricohalim.movieapps.detail.DetailMovieViewModel
import com.andricohalim.movieapps.di.AppScope
import com.andricohalim.movieapps.favorite.FavoriteViewModel
import com.andricohalim.movieapps.home.HomeViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(
    private val creators : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]?: creators.entries.firstOrNull{
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}