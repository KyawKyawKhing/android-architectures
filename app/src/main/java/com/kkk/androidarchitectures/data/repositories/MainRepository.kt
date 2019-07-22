package com.kkk.androidarchitectures.data.repositories

import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.network.network_response.MovieListResponse
import io.reactivex.Observable

interface MainRepository {
    fun fetchMovieData(): Observable<MovieListResponse>
    fun saveDataIntoDatabase(movieList:List<MovieVO>)
}