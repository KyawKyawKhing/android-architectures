package com.kkk.androidarchitectures.viewmodels

import androidx.lifecycle.MutableLiveData
import com.kkk.androidarchitectures.data.repositories.MainRepository
import com.kkk.androidarchitectures.data.vos.MovieVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(
    private val mainRepo: MainRepository
) : BaseViewModel() {
    var errorState = MutableLiveData<String>()
    var successState = MutableLiveData<List<MovieVO>>()

    fun loadMovieList() {
        launch {
            mainRepo.fetchMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    successState.postValue(it.movieList)
                }, {
                    errorState.value = it.localizedMessage
                })

        }
    }

}