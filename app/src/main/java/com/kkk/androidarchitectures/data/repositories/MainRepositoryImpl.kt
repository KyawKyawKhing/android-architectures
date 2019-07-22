package com.kkk.androidarchitectures.data.repositories

import com.kkk.androidarchitectures.network.ApiService
import com.kkk.androidarchitectures.network.network_response.MovieListResponse
import io.reactivex.Observable

class MainRepositoryImpl(private val mApiService: ApiService) : MainRepository {
    override fun fetchMovieData(): Observable<MovieListResponse> {
        return mApiService.loadMovieList()
    }
}