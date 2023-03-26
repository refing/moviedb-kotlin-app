package com.refing.tmdbbrowserapp.core.data.source.remote.network

import com.refing.tmdbbrowserapp.core.data.source.remote.response.ResultMoviesResponse
import retrofit2.http.GET
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

    @GET("search/movie")
    suspend fun getMoviesSearch(
        @Query("api_key") api_key: String?,
        @Query("query") query: String?
    ): ResultMoviesResponse

}