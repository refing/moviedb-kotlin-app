package com.refing.tmdbbrowserapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1,  exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao


}