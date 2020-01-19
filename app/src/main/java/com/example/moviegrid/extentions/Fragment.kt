package com.example.moviegrid.extentions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(
    message: String,
    positiveResId: Int,
    duration: Int,
    listener: View.OnClickListener
) {
    view?.let { view ->
        val snackbar = Snackbar.make(view, message, duration)
        snackbar.setAction(positiveResId, listener)
        snackbar.show()
    }
}

fun Fragment.setupSwipeToRefresh(swipeRefresh: SwipeRefreshLayout, listener: () -> Unit) {
    swipeRefresh.setOnRefreshListener {
        listener.invoke()
    }
    swipeRefresh.addClolors()
}