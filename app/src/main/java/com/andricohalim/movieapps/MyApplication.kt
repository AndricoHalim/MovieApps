package com.andricohalim.movieapps

import android.app.Application
import com.andricohalim.movieapps.core.di.CoreComponent
import com.andricohalim.movieapps.core.di.DaggerCoreComponent
import com.andricohalim.movieapps.di.AppComponent
import com.andricohalim.movieapps.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}