package com.kkk.androidarchitectures.data.models

import com.kkk.androidarchitectures.network.network_response.NoticeListResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NoticeModel : BaseModel() {
    private var INSTANCE: NoticeModel? = null
    val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun getInstance(): NoticeModel {
        if (INSTANCE == null) {
            INSTANCE = NoticeModel()
        }
        return INSTANCE!!
    }

    fun loadNoticeListData(onSuccess: (response: NoticeListResponse) -> Unit, onError: (error: Throwable) -> Unit) {
        disposable.add(
            mApiService.loadNoticeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(it)
                }, {
                    onError(it)
                })
        )
//            .subscribe(object : Observer<NoticeListResponse> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(noticeList: NoticeListResponse) {
////                    if (noticeList != null) {
////                        homePresenter.displayData(noticeList!!.getNoticeList())
////                    }
//                }
//
//                override fun onError(e: Throwable) {
////                    homePresenter.displayError(e.message)
//                }
//
//                override fun onComplete() {
//
//                }
//            })
    }
}