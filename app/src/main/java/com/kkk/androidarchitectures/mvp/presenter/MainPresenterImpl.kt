package com.kkk.androidarchitectures.mvp.presenter

import androidx.lifecycle.MutableLiveData
import com.kkk.androidarchitectures.data.repositories.MainRepository
import com.kkk.androidarchitectures.mvp.contract.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl(private val repository: MainRepository) : MainContract.MainPresenter {

    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }
    private var mView: MainContract.MainView? = null

    override fun attachView(view: MainContract.MainView) {
        this.mView = view
    }

    override fun loadMovieList() {
        repository.movieData
            .observeForever {
                disposable.add(
                    it
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            repository.saveDataIntoDatabase(it.movieList)
                        }
                        .subscribe({ response ->
                            repository.movieData = MutableLiveData()
                            mView?.showMovieList(response.movieList)
                        }, { error ->
                            mView?.showError(error.localizedMessage!!)
                        })
                )
            }


        repository.fetchMovieData()
    }

    override fun detachView() {
        this.mView = null
        disposable.clear()
    }
}