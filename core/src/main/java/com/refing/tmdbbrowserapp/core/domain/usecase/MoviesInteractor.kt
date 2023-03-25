package com.refing.tmdbbrowserapp.core.domain.usecase

import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository

class MoviesInteractor (private val movieRepository: InterfaceMoviesRepository): MoviesUseCase {

    override fun getPopularMovies() = movieRepository.getPopularMovies()

    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovies(movie, state)
}