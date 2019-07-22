package com.kkk.androidarchitectures.mvp.contract

import com.kkk.androidarchitectures.data.vos.MovieVO

interface MainContract{

    interface MainView{
        fun showMovieList(movieList:List<MovieVO>)
        fun showError(error:String)
    }

    interface MainPresenter{
        fun attachView(view: MainView)
        fun loadMovieList()
        fun detachView()
    }
}