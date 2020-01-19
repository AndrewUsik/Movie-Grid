package com.example.moviegrid

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.moviegrid.core.App
import com.example.moviegrid.core.di.ApplicationProvider
import com.example.moviegrid.di.ApplicationComponent
import com.example.moviegrid.prefs.ApplicationPreferences
import javax.inject.Inject

class MovieGridApp: MultiDexApplication(), App {
    @Inject
    lateinit var applicationPreferences: ApplicationPreferences

    companion object {
        lateinit var appComponent: ApplicationComponent

        fun get(context: Context): MovieGridApp {
            return context.applicationContext as MovieGridApp
        }
    }

    override fun getAppComponent(): ApplicationProvider {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = ApplicationComponent.Initializer.init(this@MovieGridApp)
        appComponent.inject(this)
    }

}