package com.refing.tmdbbrowserapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val userDao: MoviesDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: MoviesDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = userDao.getAllMovies()


    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = userDao.getFavoriteMovies()

    fun insertMovies(userList: List<MovieEntity>) = userDao.insertMovies(userList)

    fun setFavoriteMovies(user: MovieEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteMovies(user)
    }
}