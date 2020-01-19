package com.example.moviegrid.data

import com.example.moviegrid.domain.Schedulers
import io.reactivex.Scheduler
import javax.inject.Inject

class AppSchedulers @Inject constructor() : Schedulers {
    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.computation()
    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}