package com.example.moviegrid.extentions

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviegrid.di.GlideApp


fun loadImage(context: Context, view: ImageView, url: String? = null, uri: Uri? = null) {
    GlideApp.with(context)
        .load(url ?: uri)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
