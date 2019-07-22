package com.kkk.androidarchitectures.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.models.MovieModel
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.mvp.contract.MainContract
import com.kkk.androidarchitectures.mvp.presenter.MainPresenterImpl
import com.kkk.androidarchitectures.ui.adapters.MovieListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val mAdapter: MovieListAdapter by lazy { MovieListAdapter(this::onClickNoticeListItem) }

    private val mPresenter: MainContract.MainPresenter by lazy { MainPresenterImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotice.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = mAdapter
        }

        mPresenter.attachView(this)
        mPresenter.loadMovieList()
    }

    private fun onClickNoticeListItem(data: MovieVO) {
        Toast.makeText(applicationContext, "You clicked at ${data.title}", Toast.LENGTH_SHORT).show()
    }


    override fun showMovieList(movieList: List<MovieVO>) {
        this.mAdapter.setNewList(movieList)
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }
}
