package com.kkk.androidarchitectures.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kkk.androidarchitectures.R
import com.kkk.androidarchitectures.data.models.NoticeModel
import com.kkk.androidarchitectures.data.vos.NoticeVO
import com.kkk.androidarchitectures.ui.adapters.NoticeListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val noticeModel: NoticeModel by lazy { NoticeModel().getInstance() }
    private val mAdapter: NoticeListAdapter by lazy { NoticeListAdapter(this::onClickNoticeListItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNotice.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = mAdapter
        }
        noticeModel.loadNoticeListData({
            this.mAdapter.setNewList(it.noticeList)
        }, {
            Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun onClickNoticeListItem(noticeVo: NoticeVO) {
        //use like this
//        val intent = AddContactInfoActivity.newActivity(
//            this,
//            id = contact.id,
//            name = contact.name,
//            phone = contact.phone,
//            address = contact.address
//        )
//        startActivity(intent)
    }
}
