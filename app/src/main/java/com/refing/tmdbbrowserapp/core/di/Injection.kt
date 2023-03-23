package com.refing.tmdbbrowserapp.core.di

import android.content.Context
import com.refing.tmdbbrowserapp.core.data.source.local.LocalDataSource
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesRoomDatabase
import com.refing.tmdbbrowserapp.core.data.source.remote.RemoteDataSource
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiConfig
import com.refing.tmdbbrowserapp.core.data.source.repository.MoviesRepository
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesInteractor
import com.refing.tmdbbrowserapp.core.domain.usecase.MoviesUseCase
import com.refing.tmdbbrowserapp.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): InterfaceMoviesRepository {
        val database = MoviesRoomDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMoviesUseCase(context: Context): MoviesUseCase {
        val repository = provideRepository(context)
        return MoviesInteractor(repository)
    }
}
