package com.kkk.androidarchitectures.data.repositories

import androidx.lifecycle.MutableLiveData
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.network.network_response.MovieListResponse
import io.reactivex.Observable

interface MainRepository {
    var movieData:MutableLiveData<Observable<MovieListResponse>>
    fun fetchMovieData()
    fun saveDataIntoDatabase(movieList:List<MovieVO>)
}