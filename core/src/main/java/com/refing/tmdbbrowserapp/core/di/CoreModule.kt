package com.refing.tmdbbrowserapp.core.di

import androidx.room.Room
import com.refing.tmdbbrowserapp.core.data.MoviesRepository
import com.refing.tmdbbrowserapp.core.data.source.local.LocalDataSource
import com.refing.tmdbbrowserapp.core.data.source.local.room.MoviesRoomDatabase
import com.refing.tmdbbrowserapp.core.data.source.remote.RemoteDataSource
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiService
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository
import com.refing.tmdbbrowserapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesRoomDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesRoomDatabase::class.java, "MoviesTMDB.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<InterfaceMoviesRepository> {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}