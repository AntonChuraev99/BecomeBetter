package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.BottomNavigationPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.BottomNavigationView
import com.antonchuraev.becomebetter.helpers.extensions.toast
import com.antonchuraev.becomebetter.views.goals.GoalInListView
import moxy.presenter.InjectPresenter


class AllGoalsFragment : BaseFragment<FragmentAllGoalsBinding>() , AllGoalsView {

    @InjectPresenter
    lateinit var presenter: AllGoalsPresenter

    override val layoutView: Int = R.layout.fragment_all_goals

    override fun onCreateView(rootView: View) {
        presenter.initializeThreeFirstGoalsData()

        setListeners()

    }

    private fun setListeners() {
        binding.llCreateFirstGoal.setOnClickListener {
            appRouter.replaceScreen ( Screens.Screen.BottomNavigation( NavigationTab.ADD_GOAL ) )
        }

        binding.llAllMyGoals.setOnClickListener {
            appRouter.navigateTo ( Screens.Screen.AllGoals.MyGoals() )
        }
    }


    companion object {
        fun newInstance() = AllGoalsFragment().apply {

        }
    }

    override fun setThreeFirstGoalViews(goals: List<Goal>) {

    }


}