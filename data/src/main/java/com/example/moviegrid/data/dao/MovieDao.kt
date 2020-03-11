package com.example.moviegrid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviegrid.data.model.MovieLocalModel
import io.reactivex.Maybe

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovieById(id: Int): Maybe<MovieLocalModel>

    @Query("SELECT * FROM Movie LIMIT :limit OFFSET :offset")
    fun getMoviesByPage(limit: Int, offset: Int): Maybe<List<MovieLocalModel>>

    @Query("SELECT * FROM Movie")
    fun getAllPlayingMovies(): Maybe<List<MovieLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieLocalModel: MovieLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieLocalModel: List<MovieLocalModel>)
}
