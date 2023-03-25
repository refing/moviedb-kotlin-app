package com.refing.tmdbbrowserapp.di

import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependency {

    fun movieUseCase(): MoviesUseCase
}