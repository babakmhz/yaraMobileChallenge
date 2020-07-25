package com.android.babakmhz.yaramobilechallenge.data.db

import androidx.room.*
import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetails
import com.android.babakmhz.yaramobilechallenge.data.model.MovieWithRatings
import com.android.babakmhz.yaramobilechallenge.data.model.Ratings
import com.android.babakmhz.yaramobilechallenge.data.model.Search

@Dao
interface MoviesDao {

    @Transaction
    @Query("SELECT * from Movies order by title ASC")
    suspend fun getAllMovies(): List<Search>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Search>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetails: MovieDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRatings(ratings: List<Ratings>)

    @Transaction
    @Query("select * from Details where imdbId  = :imdbId ")
    suspend fun getMovieInDetail(imdbId: String): MovieWithRatings


}