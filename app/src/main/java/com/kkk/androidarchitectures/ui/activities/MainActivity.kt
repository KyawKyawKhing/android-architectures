package com.kkk.androidarchitectures.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.di.Injection
import com.kkk.androidarchitectures.ui.adapters.MovieListAdapter
import com.kkk.androidarchitectures.viewmodels.MainViewModel
import com.kkk.androidarchitectures.viewmodels.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAdapter: MovieListAdapter by lazy { MovieListAdapter(this::onClickNoticeListItem) }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, MainViewModelFactory(Injection.provideMainRepository(applicationContext)))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotice.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = mAdapter
        }

        mViewModel.successState.observe(this, Observer {
            this.mAdapter.setNewList(it)
        })
        mViewModel.errorState.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

        mViewModel.loadMovieList()
    }


    private fun onClickNoticeListItem(data: MovieVO) {
        Toast.makeText(applicationContext, "You clicked at ${data.title}", Toast.LENGTH_SHORT).show()
    }

}
