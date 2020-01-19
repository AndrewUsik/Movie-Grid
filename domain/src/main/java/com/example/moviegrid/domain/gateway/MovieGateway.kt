package com.example.moviegrid.domain.gateway

import com.example.moviegrid.domain.entity.Movie
import io.reactivex.Observable


interface MovieGateway {
    fun getNowPlayingMovies(
        page: Int
    ): Observable<List<Movie>>
}