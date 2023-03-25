package com.refing.tmdbbrowserapp.core.data.source.remote.network

import com.refing.tmdbbrowserapp.core.data.source.remote.response.ResultMoviesResponse
import com.refing.tmdbbrowserapp.core.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") api_key: String?
    ): ResultMoviesResponse

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key") api_key: String?
    ): ResultMoviesResponse

//    @GET("users")
//    fun getUsers(
//        @Header("Authorization") token: String?
//    ): Call<List<PreUserResponse>>

//    @GET("users/{login}")
//    fun getUserDetail(
//        @Header("Authorization") token: String?,
//        @Path("login") login: String
//    ): Call<MoviesResponse>
}