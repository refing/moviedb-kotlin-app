package com.refing.tmdbbrowserapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultMoviesResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<MoviesResponse>,

)
