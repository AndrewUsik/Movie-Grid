package com.example.moviegrid.ui.deteil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviegrid.base.BaseFragment
import com.example.moviegrid.databinding.FragmentDetailBinding
import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.extentions.formatDate
import com.example.moviegrid.extentions.getPosterUrl
import com.example.moviegrid.extentions.loadBlurImage
import com.example.moviegrid.extentions.loadImage

class DetailFragment : BaseFragment() {

    private val viewModel: DeteilViewModel by bindViewModel()

    private lateinit var binding: FragmentDetailBinding
    private lateinit var data: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            data = it.getSerializable(EXTRA_MOVIE) as Movie
        }
        initToolbar()
        initUi()
    }

    private fun initToolbar() {
        binding.run {
            toolbarContent.let {
                initToolbar(it.toolbar)
                it.titleText.text = data.title
                it.backButton.visibility = View.VISIBLE
                it.backButton.setOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    private fun initUi() {
        binding.run {
            context?.let {
                loadImage(it, imageView, data.posterPath?.getPosterUrl())
                loadBlurImage(it, backPoster, data.backdropPath?.getPosterUrl())
            }
            titleText.text = data.title
            descriptionText.text = data.overview
            scoreText.text = data.voteAverage.toString()
            releaseDateText.text = data.releaseDate.formatDate()
            ratingText.text = "R"

        }
    }

    companion object {
        private const val EXTRA_MOVIE = "extra_movie"

        fun newInstance(data: Movie): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_MOVIE, data)
            fragment.arguments = bundle
            return fragment
        }
    }
}