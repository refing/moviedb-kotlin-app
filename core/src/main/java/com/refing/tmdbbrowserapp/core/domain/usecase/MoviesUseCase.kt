package com.refing.tmdbbrowserapp.core.domain.usecase

import com.refing.tmdbbrowserapp.core.data.Resource
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovies(movie: Movie, state: Boolean)

    fun searchMovies(query:String): Flow<List<Movie>>
}