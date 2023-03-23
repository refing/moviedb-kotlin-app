package com.refing.tmdbbrowserapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.refing.tmdbbrowserapp.BuildConfig
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.network.ApiService
import com.refing.tmdbbrowserapp.core.data.source.remote.response.ResultMoviesResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private val token = BuildConfig.KEY

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        val resultDataListDetail = MutableLiveData<ApiResponse<List<MoviesResponse>>>()

        //get data from remote api
        val client = apiService.getMoviesPopular(api_key = token)
        client.enqueue(object : Callback<ResultMoviesResponse> {
            override fun onResponse(
                call: Call<ResultMoviesResponse>,
                response: Response<ResultMoviesResponse>
            ) {
                val dataArray = response.body()

                if (dataArray != null) {
                    resultDataListDetail.value = ApiResponse.Success(dataArray.results)
                }else{
                    resultDataListDetail.value = ApiResponse.Empty
                }
            }

            override fun onFailure(call: Call<ResultMoviesResponse>, t: Throwable) {
                resultDataListDetail.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultDataListDetail
    }

}