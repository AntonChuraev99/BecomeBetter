package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import android.view.View
import com.antonchuraev.becomebetter.base.FlowFragment
import com.antonchuraev.becomebetter.base.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen


class BottomNavigationFlowFragment : FlowFragment() {
    override val launchScreen: SupportAppScreen = Screens.Screen.BottomNavigation()

    override fun onCreateView(rootView: View) {

    }


    companion object {
        fun newInstance() = BottomNavigationFlowFragment().apply {
            arguments = null
        }
    }


}