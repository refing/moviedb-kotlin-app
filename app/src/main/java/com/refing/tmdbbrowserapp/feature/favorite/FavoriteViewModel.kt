package com.refing.tmdbbrowserapp.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MoviesUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

}