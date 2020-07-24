package com.android.babakmhz.yaramobilechallenge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Details")
data class DetailsResponse(
    @PrimaryKey @Expose(serialize = false, deserialize = false) val id: Int,
    @ColumnInfo @SerializedName("Title") val title: String,
    @ColumnInfo @SerializedName("Year") val year: String,
    @ColumnInfo @SerializedName("Rated") val rated: String,
    @ColumnInfo @SerializedName("Released") val released: String,
    @ColumnInfo @SerializedName("Runtime") val runtime: String,
    @ColumnInfo @SerializedName("Genre") val genre: String,
    @ColumnInfo @SerializedName("Director") val director: String,
    @ColumnInfo @SerializedName("Writer") val writer: String,
    @ColumnInfo @SerializedName("Actors") val actors: String,
    @ColumnInfo @SerializedName("Plot") val plot: String,
    @ColumnInfo @SerializedName("Language") val language: String,
    @ColumnInfo @SerializedName("Poster") val poster: String,
    @ColumnInfo @SerializedName("Ratings") val ratings: List<Ratings>,
    @ColumnInfo @SerializedName("Metascore") val metaScore: String,
    @ColumnInfo @SerializedName("imdbRating") val imdbRating: String,
    @ColumnInfo @SerializedName("imdbVotes") val imdbVotes: String,
    @ColumnInfo @SerializedName("imdbId") val imdbId: String,
    @ColumnInfo @SerializedName("Type") val type: String,
    @ColumnInfo @SerializedName("DVD") val dvd: String,
    @ColumnInfo @SerializedName("BoxOffice") val boxOffice: String,
    @ColumnInfo @SerializedName("Production") val production: String,
    @ColumnInfo @SerializedName("Website") val website: String
)

data class Ratings(
    @PrimaryKey @Expose(serialize = false, deserialize = false) val id: Int,
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)