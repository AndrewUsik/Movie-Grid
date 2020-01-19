package com.example.moviegrid.core

import android.content.Context
import com.example.moviegrid.core.di.ApplicationProvider

interface App {
    fun getApplicationContext(): Context
    fun getAppComponent(): ApplicationProvider
}