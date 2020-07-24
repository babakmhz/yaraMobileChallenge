package com.android.babakmhz.yaramobilechallenge


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
        val response = apiService.getMovies()
        if (response.search.isNotEmpty()) {
            cacheMovies(response.search)
            return response.search
        }
        return getAllMoviesFromDb()
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
            deserializer.website
        )
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