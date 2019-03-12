package io.github.viniciusalvesmello.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(
    private val schedulerIO: Scheduler,
    private val androidSchedulersMainThread: Scheduler,
    private val compositeDisposable: CompositeDisposable
) {

    protected abstract fun buildSingleUseCaseObservable(params: Params): Single<T>

    open fun run(singleObserver: DisposableSingleObserver<T>, params: Params) {
        val single = this.buildSingleUseCaseObservable(params)
            .subscribeOn(schedulerIO)
            .observeOn(androidSchedulersMainThread) as Single<T>
        addDisposable(single.subscribeWith(singleObserver))
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}