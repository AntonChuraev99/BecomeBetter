package com.antonchuraev.becomebetter.fragments.test

import android.util.Log
import com.antonchuraev.becomebetter.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class TestPresenter: BasePresenter<TestView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d( tag , "onFirstViewAttach() called")
    }


}