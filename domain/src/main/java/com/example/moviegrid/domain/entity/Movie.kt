package com.example.moviegrid.domain.entity

import java.io.Serializable

data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String?,
    val popularity: Double,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
): Serializable