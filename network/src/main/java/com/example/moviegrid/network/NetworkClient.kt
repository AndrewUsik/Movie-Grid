package com.example.moviegrid.network

import com.example.moviegrid.network.api.ApiMoviesService
import com.example.moviegrid.network.model.MoviesResponse
import io.reactivex.Single
import javax.inject.Inject

interface MovieApi {
    fun getNowPlayingMovies(page: Int): Single<MoviesResponse>
}

interface NetworkClient : MovieApi {

    class NetworkClientImpl @Inject constructor(
        private val movieService: ApiMoviesService
    ) : NetworkClient {
        override fun getNowPlayingMovies(page: Int): Single<MoviesResponse> {
              return  movieService.getNowPlayingMovies(apiKey = BuildConfig.API_KEY, page = page)
        }
    }
}