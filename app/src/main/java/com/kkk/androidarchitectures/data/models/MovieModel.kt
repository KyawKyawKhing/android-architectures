package com.kkk.androidarchitectures.data.models

import com.kkk.androidarchitectures.network.network_response.MovieListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieModel : BaseModel() {
    private var INSTANCE: MovieModel? = null
    val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun getInstance(): MovieModel {
        if (INSTANCE == null) {
            INSTANCE = MovieModel()
        }
        return INSTANCE!!
    }

    fun loadMovieListData(onSuccess: (response: MovieListResponse) -> Unit, onError: (error: Throwable) -> Unit) {
        disposable.add(
            mApiService.loadMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(it)
                }, {
                    onError(it)
                })
        )
//            .subscribe(object : Observer<MovieListResponse> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(movieList: MovieListResponse) {
////                    if (movieList != null) {
////                        homePresenter.displayData(movieList!!.getMovieList())
////                    }
//                }
//
//                override fun onError(e: Throwable) {
////                    homePresenter.displayError(e.message)
//                }
//
//                override fun onComplete() {
//
//                }
//            })
    }
}