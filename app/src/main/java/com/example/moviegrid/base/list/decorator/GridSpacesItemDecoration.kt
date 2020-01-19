package com.example.moviegrid.base.list.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject


class GridSpacesItemDecoration @Inject constructor() : RecyclerView.ItemDecoration() {
    var space: Int = 0
    private var columCount: Int = 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos = parent.getChildAdapterPosition(view)
        if (pos == 0 || (pos > 0 && pos % 2 == 0)) {
            outRect.right = space
        }
        if (pos + 1 > columCount) {
            outRect.top = space
        }

        if (pos <= 1) {
            outRect.top = space
        }

    }
}