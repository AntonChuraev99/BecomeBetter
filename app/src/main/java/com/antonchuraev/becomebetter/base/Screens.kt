package com.antonchuraev.becomebetter.base

import androidx.fragment.app.Fragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.BottomNavigationFragment
import com.antonchuraev.becomebetter.fragments.splash.SplashFlowFragment
import com.antonchuraev.becomebetter.fragments.splash.SplashFragment
import com.antonchuraev.becomebetter.fragments.test.TestFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object Flow{

        class SplashFlow:SupportAppScreen(){
             override fun getFragment(): Fragment = SplashFlowFragment.newInstance()
        }

        /*class MainFlow:SupportAppScreen(){
            override fun getFragment(): Fragment = MainFlowFragment.newInstance()
        }*/
    }

    object Test {
        class Test: SupportAppScreen(){
            override fun getFragment(): Fragment = TestFragment.newInstance()
        }
    }

    object Screen {
        class Splash: SupportAppScreen(){
            override fun getFragment(): Fragment = SplashFragment.newInstance()
        }

        /**
         * главный экран с нижней навигацией
         */
        class BottomNavigation: SupportAppScreen(){
            override fun getFragment(): Fragment = BottomNavigationFragment.newInstance()
        }


    }

}