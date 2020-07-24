package com.android.babakmhz.yaramobilechallenge.data.network

import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetailsSerializer
import com.android.babakmhz.yaramobilechallenge.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {


    @GET
    suspend fun getMovies(
        @Query("apikey") apikey: String
        , @Query("s") search: String
    ): Movies

    @GET
    suspend fun getMovieDetails(
        @Query("apikey") apikey: String,
        @Query("i") imdbId: String
    ): MovieDetailsSerializer
}