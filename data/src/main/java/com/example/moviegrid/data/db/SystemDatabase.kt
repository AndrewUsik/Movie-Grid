package com.example.moviegrid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviegrid.data.dao.MovieDao
import com.example.moviegrid.data.model.MovieLocalModel

@Database(entities = [MovieLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SystemDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    companion object {
        private const val DB_NAME = "movie_grid.db"
        fun newInstance(context: Context): SystemDatabase {
            return Room.databaseBuilder(context, SystemDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}