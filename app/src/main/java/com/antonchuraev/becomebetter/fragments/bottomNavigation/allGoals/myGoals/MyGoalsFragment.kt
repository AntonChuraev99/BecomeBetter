package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.databinding.FragmentMyGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsView
import moxy.presenter.InjectPresenter


class MyGoalsFragment : BaseFragment<FragmentMyGoalsBinding>() , MyGoalsView {

    @InjectPresenter
    lateinit var presenter: MyGoalsGoalsPresenter

    override val layoutView: Int = R.layout.fragment_my_goals

    override fun onCreateView(rootView: View) {
        enableToolbar()

        setListeners()
    }

    private fun enableToolbar() {
        setDefaultToolbar(R.string.all_my_goals)
    }

    private fun setListeners() {

    }


    companion object {
        fun newInstance() = MyGoalsFragment().apply {

        }
    }


}