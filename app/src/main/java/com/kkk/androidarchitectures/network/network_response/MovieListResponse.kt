package com.kkk.androidarchitectures.network.network_response

import com.google.gson.annotations.SerializedName
import com.kkk.androidarchitectures.data.vos.MovieVO

class MovieListResponse {
    @SerializedName("success")
    var sucess: String? = null
    @SerializedName("movie_list")
    var movieList: List<MovieVO> = mutableListOf()
}