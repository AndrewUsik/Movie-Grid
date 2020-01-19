package com.example.moviegrid.base.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<out T1 : View> constructor(val view: T1) : RecyclerView.ViewHolder(view)