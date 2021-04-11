package com.antonchuraev.becomebetter.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView
import org.koin.core.KoinComponent

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>(), KoinComponent {
    val tag = this.javaClass.toString()

    private val disposables = CompositeDisposable()

    protected fun Disposable.unsubscribeOnDestroy() {
        disposables.add(this)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}