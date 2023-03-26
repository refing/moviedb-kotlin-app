package com.refing.tmdbbrowserapp.di

import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesInteractor
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import com.refing.tmdbbrowserapp.feature.detail.DetailViewModel
import com.refing.tmdbbrowserapp.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}