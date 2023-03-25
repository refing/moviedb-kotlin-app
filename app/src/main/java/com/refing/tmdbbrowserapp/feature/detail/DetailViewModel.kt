package com.refing.tmdbbrowserapp.feature.detail

import androidx.lifecycle.ViewModel
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val movieUseCase: MoviesUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovies(movie, newStatus)
}