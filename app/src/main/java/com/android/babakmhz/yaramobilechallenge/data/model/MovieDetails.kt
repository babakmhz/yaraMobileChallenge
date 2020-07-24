package com.android.babakmhz.yaramobilechallenge.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

data class MovieDetailsSerializer(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Ratings") val ratings: List<Ratings>,
    @SerializedName("Metascore") val metaScore: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("imdbVotes") val imdbVotes: String,
    @SerializedName("imdbId") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("DVD") val dvd: String,
    @SerializedName("BoxOffice") val boxOffice: String,
    @SerializedName("Production") val production: String,
    @SerializedName("Website") val website: String
)

@Entity(tableName = "Details")
data class MovieDetails(
    @ColumnInfo val title: String,
    @ColumnInfo val year: String,
    @ColumnInfo val rated: String,
    @ColumnInfo val released: String,
    @ColumnInfo val runtime: String,
    @ColumnInfo val genre: String,
    @ColumnInfo val director: String,
    @ColumnInfo val writer: String,
    @ColumnInfo val actors: String,
    @ColumnInfo val plot: String,
    @ColumnInfo val language: String,
    @ColumnInfo val poster: String,
    @ColumnInfo val metaScore: String,
    @ColumnInfo val imdbRating: String,
    @ColumnInfo val imdbVotes: String,
    @PrimaryKey val imdbId: String,
    @ColumnInfo val type: String,
    @ColumnInfo val dvd: String,
    @ColumnInfo val boxOffice: String,
    @ColumnInfo val production: String,
    @ColumnInfo val website: String
)

data class MovieWithRatings(
    @Embedded val movieDetails: MovieDetails,
    @Relation(
        parentColumn = "imdbId",
        entityColumn = "source"
    ) val ratings: List<Ratings>
)

@Entity
data class Ratings(
    @PrimaryKey
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String
)