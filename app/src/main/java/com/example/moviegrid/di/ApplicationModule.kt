package com.example.moviegrid.di

import android.content.Context
import android.content.SharedPreferences
import com.example.moviegrid.core.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    private const val FILE_NAME = "movie.settings"

    @JvmStatic
    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences {
        return app.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }
}