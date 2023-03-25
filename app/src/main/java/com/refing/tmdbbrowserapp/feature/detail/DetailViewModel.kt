package com.refing.tmdbbrowserapp.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MoviesUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovies(movie, newStatus)


    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}