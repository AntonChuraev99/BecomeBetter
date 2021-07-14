package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.helpers.extensions.toast
import com.antonchuraev.becomebetter.views.goals.GoalInListView
import moxy.presenter.InjectPresenter


class AllGoalsFragment : BaseFragment<FragmentAllGoalsBinding>() , AllGoalsView {

    @InjectPresenter
    lateinit var presenter: AllGoalsPresenter


    override val layoutView: Int = R.layout.fragment_all_goals




    override fun onCreateView(rootView: View) {
        presenter.initializeThreeFirstGoalsData()


    }




    companion object {

        fun newInstance() = AllGoalsFragment().apply {

        }
    }

    override fun setThreeFirstGoalViews(goals: List<Goal>) {
        //context?.toast("${goals.size}")


    }

}