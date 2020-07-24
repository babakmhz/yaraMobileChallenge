package com.android.babakmhz.yaramobilechallenge.data.network

import com.android.babakmhz.yaramobilechallenge.data.model.MovieDetailsSerializer
import com.android.babakmhz.yaramobilechallenge.data.model.Movies
import com.android.babakmhz.yaramobilechallenge.utils.API_KEY
import com.android.babakmhz.yaramobilechallenge.utils.SEARCH_KEY
import org.koin.core.KoinComponent
import org.koin.core.inject

class ApiService : KoinComponent {

    private val apiHelper: ApiHelper by inject()

    suspend fun getMovies(apikey: String = API_KEY, s: String = SEARCH_KEY): Movies {
        return apiHelper.getMovies(apikey, search = s)
    }

    suspend fun getMovieInDetail(
        apikey: String = API_KEY,
        imdbId: String
    ): MovieDetailsSerializer {
        return apiHelper.getMovieDetails(apikey, imdbId)
    }
}