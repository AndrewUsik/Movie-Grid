package com.example.moviegrid.data.repository

import com.example.moviegrid.data.local.MovieLocalDataSource
import com.example.moviegrid.data.model.MovieLocalModel
import com.example.moviegrid.data.remote.MovieRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {

    fun getPlayingMovies(page: Int): Observable<List<MovieLocalModel>> {
        val offset = page * ITEMS_LIMIT - ITEMS_LIMIT
        val local = localDataSource.getMoviesByPage(limit = ITEMS_LIMIT, offset = offset)

        val remote = remoteDataSource.getPlayingMovies(page).map {
            it.results.map { result ->
                MovieLocalModel(
                    adult = result.adult,
                    backdropPath = result.backdropPath,
                    genreIds = result.genreIds,
                    id = result.id,
                    originalLanguage = result.originalLanguage,
                    originalTitle = result.originalTitle,
                    overview = result.overview,
                    releaseDate = result.releaseDate,
                    posterPath = result.posterPath,
                    popularity = result.popularity,
                    title = result.title,
                    video = result.video,
                    voteAverage = result.voteAverage,
                    voteCount = result.voteCount
                )
            }
        }.doOnNext {
            it?.let {
                localDataSource.insert(it)
            }
        }
        return Observable.concat(local, remote)
    }

    companion object {
        private const val ITEMS_LIMIT = 20
    }
}