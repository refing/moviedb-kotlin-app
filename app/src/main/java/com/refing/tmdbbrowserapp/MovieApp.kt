package com.refing.tmdbbrowserapp

import android.app.Application
import com.refing.tmdbbrowserapp.core.di.databaseModule
import com.refing.tmdbbrowserapp.core.di.networkModule
import com.refing.tmdbbrowserapp.core.di.repositoryModule
import com.refing.tmdbbrowserapp.di.useCaseModule
import com.refing.tmdbbrowserapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MovieApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}