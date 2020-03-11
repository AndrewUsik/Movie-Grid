package com.example.moviegrid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieLocalModel(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
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
)