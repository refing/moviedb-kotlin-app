package com.refing.tmdbbrowserapp.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(moviesUseCase: MoviesUseCase) : ViewModel() {
    val popularmovies = moviesUseCase.getPopularMovies().asLiveData()

    val upcomingmovies = moviesUseCase.getUpcomingMovies().asLiveData()
}