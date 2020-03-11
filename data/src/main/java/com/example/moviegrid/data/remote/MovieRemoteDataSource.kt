package com.example.moviegrid.data.remote

import com.example.moviegrid.network.NetworkClient
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val networkClient: NetworkClient
) {
    fun getPlayingMovies(page: Int) = networkClient.getNowPlayingMovies(page).toObservable()
}