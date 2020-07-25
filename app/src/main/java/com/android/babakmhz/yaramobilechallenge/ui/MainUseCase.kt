package com.android.babakmhz.yaramobilechallenge.ui


import com.android.babakmhz.yaramobilechallenge.data.db.MoviesDao
import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetails
import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetailsSerializer
import com.android.babakmhz.yaramobilechallenge.data.model.MovieWithRatings
import com.android.babakmhz.yaramobilechallenge.data.model.Search
import com.android.babakmhz.yaramobilechallenge.data.network.ApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainUseCase : KoinComponent {

    private val apiService: ApiService by inject()
    private val db: MoviesDao by inject()

    suspend fun getMovies(): List<Search>? {
         return try {
            val response = apiService.getMovies()
            cacheMovies(response.search)
            response.search
        } catch (e: java.lang.Exception) {
            getAllMoviesFromDb()
        }
    }


    private suspend fun getMovieInDetailFromDb(imdbId: String): MovieWithRatings? {
        return try {
            db.getMovieInDetail(imdbId)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMovieInDetail(imdbId: String): MovieWithRatings? {
        return try {
            val response = apiService.getMovieInDetail(imdbId = imdbId)
            cacheMovieInDetail(response)
        } catch (e: Exception) {
            getMovieInDetailFromDb(imdbId)
        }
    }

    private suspend fun cacheMovieInDetail(deserializer: MovieDetailsSerializer): MovieWithRatings {
        // TODO: 7/24/20 clean this via editing methods in model
        val movieDetails = MovieDetails(
            deserializer.title,
            deserializer.year,
            deserializer.rated,
            deserializer.released,
            deserializer.runtime,
            deserializer.genre,
            deserializer.director,
            deserializer.writer,
            deserializer.actors,
            deserializer.plot,
            deserializer.language,
            deserializer.poster,
            deserializer.metaScore,
            deserializer.imdbRating,
            deserializer.imdbVotes,
            deserializer.imdbId,
            deserializer.type,
            deserializer.dvd,
            deserializer.boxOffice,
            deserializer.production,
            deserializer.website,
            deserializer.country
        )
        db.insertMovieDetail(movieDetails)
        db.insertRatings(deserializer.ratings)
        return MovieWithRatings(movieDetails, deserializer.ratings)
    }

    private suspend fun cacheMovies(searches: List<Search>) {
        db.insertMovies(searches)
    }

    private suspend fun getAllMoviesFromDb(): List<Search>? {
        return try {
            db.getAllMovies()
        } catch (e: Exception) {
            null
        }

    }
}