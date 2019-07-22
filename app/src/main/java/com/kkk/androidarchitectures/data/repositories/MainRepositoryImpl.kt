package com.kkk.androidarchitectures.data.repositories

import android.content.Context
import com.kkk.androidarchitectures.data.db.MyDatabase
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.network.ApiService
import com.kkk.androidarchitectures.network.network_response.MovieListResponse
import com.kkk.androidarchitectures.util.Utils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(
    private val context: Context,
    private val mApiService: ApiService,
    private val database: MyDatabase
) : MainRepository {
    override fun fetchMovieData(): Observable<MovieListResponse> {

        if (Utils.isOnline(context)) {
            return mApiService.loadMovieList()
        } else {
            val localMovieDataList = database.movieDao().allData
            val responseData = MovieListResponse()
            responseData.movieList = localMovieDataList
            return Observable.just(responseData)
        }
    }

    override fun saveDataIntoDatabase(movieList: List<MovieVO>) {
        database.movieDao().insertAll(movieList)
    }
}