package com.antonchuraev.becomebetter.base.koin

import android.content.Context
import android.media.AudioManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import org.xml.sax.ErrorHandler

internal val appModule = module {
    factory { androidContext().resources }

}