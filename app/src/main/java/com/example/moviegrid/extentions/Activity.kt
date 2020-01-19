package com.example.moviegrid.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat


inline val buildIsLollipopAndUp: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP


inline var Activity.statusBarColor: Int
    @SuppressLint("NewApi")
    get() = if (buildIsLollipopAndUp) window.statusBarColor else Color.BLACK
    @SuppressLint("NewApi")
    set(value) {
        if (buildIsLollipopAndUp) window.statusBarColor = value
    }

val Activity.sceneTransitionAnimationBundle: Bundle?
    get() = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()
