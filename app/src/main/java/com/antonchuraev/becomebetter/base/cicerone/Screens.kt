package com.antonchuraev.becomebetter.base.cicerone

import androidx.fragment.app.Fragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import com.antonchuraev.becomebetter.fragments.tabs.TabsNavigationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {


    object Screen {


        /**
         * главный экран с табами
         */
        class TabsScreen(val tab: NavigationTab = NavigationTab.MOTIVATION): SupportAppScreen(){
            override fun getFragment(): Fragment = TabsNavigationFragment.newInstance(tab)
        }


    }

}