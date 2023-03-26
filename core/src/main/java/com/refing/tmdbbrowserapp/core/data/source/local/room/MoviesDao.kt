package com.refing.tmdbbrowserapp.core.data.source.local.room

import androidx.room.*
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MoviesTMDB")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MoviesTMDB where isUpcoming = 1")
    fun getPopularMovies(): Flow<List<MovieEntity>>


    @Query("SELECT * FROM MoviesTMDB where isPopular = 1")
    fun getUpcomingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MoviesTMDB where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovies(movie: MovieEntity)

}