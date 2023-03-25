package com.refing.tmdbbrowserapp.core.di

import com.refing.tmdbbrowserapp.core.data.source.repository.MoviesRepository
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: MoviesRepository): InterfaceMoviesRepository

}