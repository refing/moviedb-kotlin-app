package com.refing.tmdbbrowserapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.refing.tmdbbrowserapp.core.data.source.Resource
import com.refing.tmdbbrowserapp.core.domain.model.Movie

interface InterfaceMoviesRepository {
    fun getAllMovies(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovies(): LiveData<List<Movie>>

    fun setFavoriteMovies(user: Movie, state: Boolean)
}