package com.refing.tmdbbrowserapp.core.utils

import com.refing.tmdbbrowserapp.core.data.source.local.entity.MovieEntity
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import com.refing.tmdbbrowserapp.core.domain.model.Movie

object DataMapper {


    fun mapResponsesToEntitiesPopular(input: List<MoviesResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                name = it.title,
                description = it.overview,
                photo = it.poster_path,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                isPopular = false,
                isUpcoming = true,
            )
            movieList.add(movie)
        }
        return movieList
    }
    fun mapResponsesToEntitiesUpcoming(input: List<MoviesResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                name = it.title,
                description = it.overview,
                photo = it.poster_path,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                isPopular = true,
                isUpcoming = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                name = it.name,
                description = it.description,
                photo = it.photo,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                isPopular = it.isPopular,
                isUpcoming = it.isUpcoming,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        photo = input.photo,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
        isPopular = input.isPopular,
        isUpcoming = input.isUpcoming,
        isFavorite = input.isFavorite
    )
}