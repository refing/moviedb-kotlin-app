package com.refing.tmdbbrowserapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun userDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null

        fun getInstance(context: Context): MoviesRoomDatabase =
            INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MoviesRoomDatabase::class.java,
                "MoviesTMDB.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}