package com.antonchuraev.becomebetter.fragments.splash

import android.view.View
import com.antonchuraev.becomebetter.base.FlowFragment
import com.antonchuraev.becomebetter.base.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SplashFlowFragment : FlowFragment() {
    override val launchScreen: SupportAppScreen = Screens.Screen.Splash()

    override fun onCreateView(rootView: View) {

    }


    companion object {
        fun newInstance() = SplashFlowFragment().apply {
            arguments = null
        }
    }


}