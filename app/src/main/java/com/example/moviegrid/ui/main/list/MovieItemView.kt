package com.example.moviegrid.ui.main.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.moviegrid.base.list.AdapterClickListener
import com.example.moviegrid.databinding.ItemViewMovieBinding
import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.extentions.getPosterUrl
import com.example.moviegrid.extentions.loadImage
import no.multimedianordic.roomr.platform.list.ItemModel

class MovieItemView : RelativeLayout, ItemModel<Movie> {
    private lateinit var listener: AdapterClickListener<Movie>
    private lateinit var data: Movie
    private val binding = ItemViewMovieBinding.inflate(
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
        this,
        true
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    fun setClickListener(listener: AdapterClickListener<Movie>) {
        this.listener = listener
    }

    override fun setData(data: Movie) {
        this.data = data
        with(binding) {
            loadImage(context, imageView, data.posterPath?.getPosterUrl())
            container.setOnClickListener {
                listener.onItemClick(data)
            }
        }
    }
}