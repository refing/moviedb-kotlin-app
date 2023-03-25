package com.refing.tmdbbrowserapp.core.data

import com.refing.tmdbbrowserapp.core.data.NetworkBoundResource
import com.refing.tmdbbrowserapp.core.data.Resource
import com.refing.tmdbbrowserapp.core.data.source.local.LocalDataSource
import com.refing.tmdbbrowserapp.core.data.source.remote.RemoteDataSource
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.core.domain.repository.InterfaceMoviesRepository
import com.refing.tmdbbrowserapp.core.utils.AppExecutors
import com.refing.tmdbbrowserapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : InterfaceMoviesRepository {


    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovies().map { DataMapper.mapEntitiesToDomain(it) }

            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                false

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesPopular(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MoviesResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getUpcomingMovies().map { DataMapper.mapEntitiesToDomain(it) }

            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                false

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getUpcomingMovies()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val movieList = DataMapper.mapResponsesToEntitiesUpcoming(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movieEntity, state) }
    }
}
