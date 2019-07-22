package com.kkk.androidarchitectures.mvp.presenter

import com.kkk.androidarchitectures.data.models.MovieModel
import com.kkk.androidarchitectures.mvp.contract.MainContract

class MainPresenterImpl:MainContract.MainPresenter {

    private val movieModel: MovieModel by lazy { MovieModel().getInstance() }
    private lateinit var mView:MainContract.MainView

    override fun attachView(view: MainContract.MainView) {
        this.mView = view
    }

    override fun loadMovieList() {
        movieModel.loadMovieListData({
            mView.showMovieList(it.movieList)
        }, {
            mView.showError(it.localizedMessage)
        })
    }
}