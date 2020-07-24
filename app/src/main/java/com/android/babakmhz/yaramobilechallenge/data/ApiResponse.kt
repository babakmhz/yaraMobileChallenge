package com.android.babakmhz.yaramobilechallenge.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("search") val search: List<Search>)

data class Search(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)