package com.refing.tmdbbrowserapp.core.data.source.remote

import android.util.Log
import com.refing.tmdbbrowserapp.core.BuildConfig
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiService
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    private val token = BuildConfig.KEY
    suspend fun getPopularMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviesPopular(api_key = token)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviesUpcoming(api_key = token)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}