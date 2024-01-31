package com.andricohalim.movieapps.core.data.source.local.room

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andricohalim.movieapps.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}