package com.example.moviegrid.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * A base class for an use case that will be executed by presentation layer
 */
abstract class UseCase<in Params, Result> internal constructor(
    private val schedulers: Schedulers
) {

    internal abstract fun buildObservable(params: Params?): Observable<Result>

    fun execute(params: Params? = null): Observable<Result> {
        return buildObservable(params)
            .subscribeOn(schedulers.subscribeOn)
            // Unfortunately RxJava had a bug that if any Exceptions were being thrown later
            // in the stream they would incorrectly cut ahead of the successful emissions
            // and break the flow.
            // In order to fix this, an overload was added in version 1.1.1
            // for observeOn(Scheduler scheduler, boolean delayError)
            // in order to signal the Scheduler to respect the delaying of errors.
            // https://medium.com/yammer-engineering/chaining-multiple-sources-with-rxjava-20eb6850e5d9
            .observeOn(schedulers.observeOn, true)
    }

    class None
}

abstract class SingleUseCase<in P, R> internal constructor(
    private val schedulers: Schedulers
) {
    internal abstract fun buildSingle(params: P?): Single<R>

    fun exec(params: P? = null): Single<R> {
        return buildSingle(params)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }
}

abstract class CompletableUseCase<in Params> internal constructor(
    private val schedulers: Schedulers
) {
    internal abstract fun buildCompletable(params: Params?): Completable

    fun execute(params: Params? = null): Completable {
        return buildCompletable(params)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }
}