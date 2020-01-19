package com.example.moviegrid.data.gateway

import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.domain.gateway.MovieGateway
import com.example.moviegrid.network.NetworkClient
import io.reactivex.Observable
import javax.inject.Inject

class MovieGatewayImpl @Inject constructor(
    private val networkClient: NetworkClient
) : MovieGateway {
    override fun getNowPlayingMovies(page: Int): Observable<List<Movie>> {
        return networkClient.getNowPlayingMovies(page).map { it ->
            it.results?.map {
                Movie(
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    posterPath = it.posterPath,
                    popularity = it.popularity,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            } ?: emptyList()
        }.toObservable()
    }
}