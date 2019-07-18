package com.kkk.androidarchitectures.network.network_response

import com.google.gson.annotations.SerializedName
import com.kkk.androidarchitectures.data.vos.NoticeVO

class NoticeListResponse {
    @SerializedName("success")
    var sucess: String? = null
    @SerializedName("movie_list")
    var noticeList: List<NoticeVO> = mutableListOf()
}