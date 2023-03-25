package com.refing.tmdbbrowserapp.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(movieUseCase: MoviesUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

}