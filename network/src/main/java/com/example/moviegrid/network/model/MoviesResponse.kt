package com.example.moviegrid.network.model


data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>? = null
)