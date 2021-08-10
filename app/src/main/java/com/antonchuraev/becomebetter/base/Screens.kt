package com.antonchuraev.becomebetter.base

import androidx.fragment.app.Fragment
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate.CreateGoalFromTemplatesFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals.MyGoalsFragment
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
        class BottomNavigation(val tab: NavigationTab = NavigationTab.ALL_GOALS): SupportAppScreen(){
            override fun getFragment(): Fragment = BottomNavigationFragment.newInstance(tab)
        }

        object AllGoals{
            class MyGoals():SupportAppScreen(){
                override fun getFragment(): Fragment = MyGoalsFragment.newInstance()
            }

        }

        object AddGoals{
            class CreateGoal(val item: Goal? = null , val mode: CreateGoalFragment.Companion.Mode = CreateGoalFragment.Companion.Mode.CREATE_NEW) : SupportAppScreen(){
                override fun getFragment(): Fragment = CreateGoalFragment.newInstance(item , mode)
            }

            class CreateGoalFromTemplate: SupportAppScreen(){
                override fun getFragment(): Fragment = CreateGoalFromTemplatesFragment.newInstance()
            }
        }

    }

}