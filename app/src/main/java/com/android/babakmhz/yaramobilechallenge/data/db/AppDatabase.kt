package com.android.babakmhz.yaramobilechallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetails
import com.android.babakmhz.yaramobilechallenge.data.model.Ratings
import com.android.babakmhz.yaramobilechallenge.data.model.Search
import com.android.babakmhz.yaramobilechallenge.utils.SCHEMA_VERSION

@Database(
    entities = [Search::class, MovieDetails::class, Ratings::class],
    version = SCHEMA_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

}