package com.kkk.androidarchitectures.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kkk.androidarchitectures.data.vos.MovieVO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieViewHolder(
    private val view: View,
    private val onClick: (movieVo: MovieVO) -> Unit
) :
    RecyclerView.ViewHolder(view) {
    fun setData(data: MovieVO) {
        view.apply {
            tvMovieTitle.text = data.title
            tvMovieBrief.text = data.brief
            Picasso.get().load(data.imageUrl).into(view.ivMovieCover)
            setOnClickListener { onClick(data) }
        }

    }
}