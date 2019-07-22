package com.kkk.androidarchitectures.data.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
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
    override var movieData: MutableLiveData<Observable<MovieListResponse>> = MutableLiveData()

    override fun fetchMovieData() {
        if (Utils.isOnline(context)) {
            movieData.postValue(mApiService.loadMovieList())
        } else {
            val localMovieDataList = database.movieDao().allData
            val disposable = CompositeDisposable()
            disposable.add(
            localMovieDataList
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    disposable.clear()
                    val responseData = MovieListResponse()
                    responseData.movieList = it
                    movieData.postValue(Observable.just(responseData))
                }
            )
        }
    }

    override fun saveDataIntoDatabase(movieList: List<MovieVO>) {
        Observable.fromCallable{database.movieDao().insertAll(movieList)}
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}