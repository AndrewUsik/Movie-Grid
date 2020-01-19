package com.example.moviegrid.domain.interactor

import com.example.moviegrid.domain.Schedulers
import com.example.moviegrid.domain.UseCase
import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.domain.gateway.MovieGateway
import io.reactivex.Observable

class MovieUseCase constructor(
    schedulers: Schedulers,
    private val signInGateway: MovieGateway
) : UseCase<MovieUseCase.Params, List<Movie>>(schedulers) {

    override fun buildObservable(params: Params?): Observable<List<Movie>> {
        val (id) = params ?: return Observable.empty()
        return signInGateway.getNowPlayingMovies(id)
    }

    data class Params(
        val page: Int
    )
}