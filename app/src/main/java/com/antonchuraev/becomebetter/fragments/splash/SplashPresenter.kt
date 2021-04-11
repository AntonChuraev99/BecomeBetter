package com.antonchuraev.becomebetter.fragments.splash

import android.util.Log
import com.antonchuraev.becomebetter.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class SplashPresenter: BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d( tag , "onFirstViewAttach() called")
    }

    fun logState(){
        Log.d( tag , " logState")
    }

    fun toTestFragment(){

    }

}