package com.refing.tmdbbrowserapp.core.data.source.local

import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MoviesDao) {

    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun getUpcomingMovies(): Flow<List<MovieEntity>> = movieDao.getUpcomingMovies()

    fun searchMoviesdb(search:String): Flow<List<MovieEntity>> = movieDao.searchMovies(search)

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovies(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovies(movie)
    }
}