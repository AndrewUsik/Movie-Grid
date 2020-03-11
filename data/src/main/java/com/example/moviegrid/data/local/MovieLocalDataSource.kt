package com.example.moviegrid.data.local

import com.example.moviegrid.data.dao.MovieDao
import com.example.moviegrid.data.model.MovieLocalModel
import io.reactivex.Observable
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    fun getMoviesByPage(limit: Int, offset: Int): Observable<List<MovieLocalModel>> =
        movieDao.getMoviesByPage(limit, offset).toObservable()

    fun insert(movieLocalModel: List<MovieLocalModel>) = movieDao.insertMovies(movieLocalModel)

}