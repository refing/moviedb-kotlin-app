package com.refing.tmdbbrowserapp.core.di

import android.content.Context
import androidx.room.Room
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesDao
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesRoomDatabase = Room.databaseBuilder(
        context,
        MoviesRoomDatabase::class.java, "Movies.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMoviesDao(database: MoviesRoomDatabase): MoviesDao = database.movieDao()
}