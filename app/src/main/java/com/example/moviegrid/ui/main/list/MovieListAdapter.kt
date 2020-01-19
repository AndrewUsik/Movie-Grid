package com.example.moviegrid.ui.main.list

import android.view.ViewGroup
import com.example.moviegrid.base.list.AdapterClickListener
import com.example.moviegrid.base.list.BaseAdapter
import com.example.moviegrid.base.list.BaseViewHolder
import com.example.moviegrid.domain.entity.Movie

class MovieListAdapter : BaseAdapter<Movie, MovieItemView, BaseViewHolder<MovieItemView>>() {

    private lateinit var listener: AdapterClickListener<Movie>

    fun setClickListener(listener: AdapterClickListener<Movie>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieItemView> {
        val itemView = MovieItemView(parent.context)
        itemView.setClickListener(listener)
        return BaseViewHolder(itemView)
    }
}