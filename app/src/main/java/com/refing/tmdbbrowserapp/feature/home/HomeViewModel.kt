package com.refing.tmdbbrowserapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase

class HomeViewModel (moviesUseCase: MoviesUseCase) : ViewModel() {
    val popularmovies = moviesUseCase.getPopularMovies().asLiveData()

    val upcomingmovies = moviesUseCase.getUpcomingMovies().asLiveData()
}