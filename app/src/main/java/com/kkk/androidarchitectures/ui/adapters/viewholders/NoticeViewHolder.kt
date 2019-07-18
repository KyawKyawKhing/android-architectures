package com.kkk.androidarchitectures.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kkk.androidarchitectures.data.vos.NoticeVO
import kotlinx.android.synthetic.main.list_item_notice.view.*

class NoticeViewHolder(
    private val view: View,
    private val onClick: (notice: NoticeVO) -> Unit
) :
    RecyclerView.ViewHolder(view) {
    fun setData(data: NoticeVO) {
        view.apply {
            tvNoticeTitle.text = data.title
            tvNoticeBrief.text = data.brief
            tvNoticeFilePath.text = data.fileSource
            tvNoticeFilePath.setOnClickListener { onClick(data) }
        }
    }
}