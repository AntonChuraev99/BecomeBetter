package ru.likebz.toolbox.cicerone

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

open class ScreenDecorator(private val screen: SupportAppScreen) : SupportAppScreen(){

    override fun getFragment(): Fragment? {
        return screen.fragment
    }

    override fun getActivityIntent(context: Context): Intent? {
        return screen.getActivityIntent(context)
    }

    override fun getScreenKey(): String {
        return screen.screenKey
    }
}