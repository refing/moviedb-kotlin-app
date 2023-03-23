package com.refing.tmdbbrowserapp.feature.main

import androidx.lifecycle.ViewModel
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies = moviesUseCase.getAllMovies()
}