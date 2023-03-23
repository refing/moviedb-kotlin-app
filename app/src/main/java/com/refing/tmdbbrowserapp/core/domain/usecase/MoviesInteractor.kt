package com.refing.tmdbbrowserapp.core.domain.usecase

import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository

class MoviesInteractor(private val userRepository: InterfaceMoviesRepository): MoviesUseCase {

    override fun getAllMovies() = userRepository.getAllMovies()

    override fun getFavoriteMovies() = userRepository.getFavoriteMovies()

    override fun setFavoriteMovies(user: Movie, state: Boolean) = userRepository.setFavoriteMovies(user, state)
}