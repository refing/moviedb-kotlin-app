package com.refing.tmdbbrowserapp.feature.detail

import androidx.lifecycle.ViewModel
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val userUseCase: MoviesUseCase) : ViewModel() {
    fun setFavoriteMovie(user: Movie, newStatus:Boolean) =
        userUseCase.setFavoriteMovies(user, newStatus)
}