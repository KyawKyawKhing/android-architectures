package com.kkk.androidarchitectures.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.models.MovieModel
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.ui.adapters.MovieListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieModel: MovieModel by lazy { MovieModel().getInstance() }
    private val mAdapter: MovieListAdapter by lazy { MovieListAdapter(this::onClickNoticeListItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotice.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = mAdapter
        }
        movieModel.loadNoticeListData({
            this.mAdapter.setNewList(it.movieList)
        }, {
            Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun onClickNoticeListItem(data: MovieVO) {
        Toast.makeText(applicationContext,"You clicked at ${data.title}",Toast.LENGTH_SHORT).show()
    }
}
