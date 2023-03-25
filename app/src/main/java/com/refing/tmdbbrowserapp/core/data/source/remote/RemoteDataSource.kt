package com.refing.tmdbbrowserapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.refing.tmdbbrowserapp.BuildConfig
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiService
import com.refing.tmdbbrowserapp.core.data.source.remote.response.ResultMoviesResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val token = BuildConfig.KEY
    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        //get data from remote api
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

}