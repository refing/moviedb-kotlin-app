package com.refing.tmdbbrowserapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MoviesTMDB")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MoviesTMDB where isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(user: List<MovieEntity>)

    @Update
    fun updateFavoriteMovies(user: MovieEntity)

}