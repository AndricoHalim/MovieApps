package com.andricohalim.movieapps.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MovieEntity(
@ColumnInfo(name = "overview")
val overview: String,

@ColumnInfo(name = "original_language")
val originalLanguage: String,

@ColumnInfo(name = "original_title")
val originalTitle: String,

@ColumnInfo(name = "title")
val title: String,

@ColumnInfo(name = "poster_path")
val posterPath: String,

@ColumnInfo(name = "backdrop_path")
val backdropPath: String,

@ColumnInfo(name = "release_date")
val releaseDate: String,

@ColumnInfo(name = "popularity")
val popularity: String,

@PrimaryKey
@ColumnInfo(name = "id")
val id: Int,

@ColumnInfo(name = "isFavorite")
var isFavorite: Boolean = false
)