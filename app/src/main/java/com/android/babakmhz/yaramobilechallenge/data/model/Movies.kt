package com.android.babakmhz.yaramobilechallenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movies(@SerializedName("Search") val search: List<Search>)

@Entity(tableName = "Movies")
data class Search(
    @ColumnInfo @SerializedName("Title") val title: String,
    @ColumnInfo @SerializedName("Year") val year: String,
    @PrimaryKey @SerializedName("imdbID") val imdbId: String,
    @ColumnInfo @SerializedName("Type") val type: String,
    @ColumnInfo @SerializedName("Poster") val poster: String
)