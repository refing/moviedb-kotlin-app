package com.refing.tmdbbrowserapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MoviesDao) {


    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()


    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovies(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovies(movie)
    }
}