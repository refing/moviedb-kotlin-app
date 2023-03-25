package com.refing.tmdbbrowserapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesTMDB")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "photo")
    var photo: String? = null,
    @ColumnInfo(name = "vote_average")
    var vote_average: Double? = null,
    @ColumnInfo(name = "vote_count")
    var vote_count: Int? = null,
    @ColumnInfo(name = "isPopular")
    var isPopular: Boolean = false,
    @ColumnInfo(name = "isUpcoming")
    var isUpcoming: Boolean = false,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


)