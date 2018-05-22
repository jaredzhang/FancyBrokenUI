package com.jaredzhang.fancybrokenui.util

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Utils {

    fun <T> applySingleSchedulers(): Single<T>.() -> SingleSource<T> = {
        observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }

}