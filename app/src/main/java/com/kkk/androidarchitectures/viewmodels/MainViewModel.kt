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
        mainRepo.movieData
            .observeForever {
                launch {
                    it
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            mainRepo.saveDataIntoDatabase(it.movieList)
                        }
                        .subscribe({ response ->
                            mainRepo.movieData = MutableLiveData()
                            successState.postValue(response.movieList)
                        }, { error ->
                            errorState.postValue(error.localizedMessage)
                        })
                }
            }


        mainRepo.fetchMovieData()
    }

}
