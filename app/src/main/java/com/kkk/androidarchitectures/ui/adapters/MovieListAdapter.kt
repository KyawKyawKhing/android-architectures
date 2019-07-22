package com.kkk.androidarchitectures.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.kkk.androidarchitectures.ui.adapters.viewholders.MovieViewHolder

class MovieListAdapter(private val onClick: (notice: MovieVO) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {
    private var movieDataList = emptyList<MovieVO>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item_movie, p0, false)
        return MovieViewHolder(view = view, onClick = onClick)
    }

    override fun getItemCount(): Int {
        return movieDataList.size
    }

    override fun onBindViewHolder(viewholder: MovieViewHolder, position: Int) {
        viewholder.setData(movieDataList[position])
    }

    fun setNewList(movieList: List<MovieVO>) {
        this.movieDataList = movieList
        notifyDataSetChanged()
    }
}