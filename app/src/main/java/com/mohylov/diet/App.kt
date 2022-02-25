package com.mohylov.diet

import android.app.Application
import android.content.Context
import com.mohylov.diet.di.AppComponent
import com.mohylov.diet.di.DaggerAppComponent


class App : Application() {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent not initialized"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder().context(this).build()
    }
}

fun Context.appComponent(): AppComponent {
    return when(this){
        is App -> appComponent
        else -> applicationContext.appComponent()
    }
}