package com.kkk.androidarchitectures.mvp.presenter

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
        disposable.add(
            repository.fetchMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { repository.saveDataIntoDatabase(it.movieList) }
                .subscribe({
                    mView?.showMovieList(it.movieList)
                }, {
                    mView?.showError(it.localizedMessage!!)
                })
        )
    }

    override fun detachView() {
        this.mView = null
        disposable.clear()
    }
}