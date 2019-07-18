package com.kkk.androidarchitectures.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.vos.NoticeVO
import com.kkk.androidarchitectures.ui.adapters.viewholders.NoticeViewHolder

class NoticeListAdapter(private val onClick: (notice: NoticeVO) -> Unit): RecyclerView.Adapter<NoticeViewHolder>() {
    private var noticeDataList = emptyList<NoticeVO>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NoticeViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item_notice, p0, false)
        return NoticeViewHolder(view = view, onClick = onClick)
    }

    override fun getItemCount(): Int {
        return noticeDataList.size
    }

    override fun onBindViewHolder(viewholder: NoticeViewHolder, position: Int) {
        viewholder.setData(noticeDataList[position])
    }

    fun setNewList(noticeList: List<NoticeVO>) {
        this.noticeDataList = noticeList
        notifyDataSetChanged()
    }
}