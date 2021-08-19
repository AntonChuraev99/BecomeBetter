package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.helpers.adapters.GoalsAdapter
import com.antonchuraev.becomebetter.helpers.extensions.add10DpDecorator
import moxy.presenter.InjectPresenter


class AllGoalsFragment : BaseFragment<FragmentAllGoalsBinding>() , AllGoalsView {

    @InjectPresenter
    lateinit var presenter: AllGoalsPresenter

    override val layoutView: Int = R.layout.fragment_all_goals

    val activeGoalsAdapter = GoalsAdapter().apply {
        selectionType = GoalsAdapter.SelectionType.HIDE_CHECKBOX
        onItemUpdateListener = {
            presenter.updateGoal(it , requireContext())
        }
    }

    override fun onCreateView(rootView: View) {
        initRv()
        setListeners()

        presenter.loadActiveGoals(requireContext())
    }

    private fun initRv() {
        binding.rvActiveGoals.apply {
            layoutManager = LinearLayoutManager(context)
            add10DpDecorator(requireContext() , DividerItemDecoration.VERTICAL)
            adapter = activeGoalsAdapter
        }
    }

    private fun setListeners() {
        binding.createFirstGoal.setOnClickListener {
            appRouter.replaceScreen ( Screens.Screen.BottomNavigation( NavigationTab.ADD_GOAL ) )
        }

        binding.llAllMyGoals.setOnClickListener {
            appRouter.navigateTo ( Screens.Screen.AllGoals.MyGoals() )
        }

        binding.ivEdit.setOnClickListener {
            activeGoalsAdapter.isSelectionEnabled = !activeGoalsAdapter.isSelectionEnabled
        }
    }

    override fun showActiveGoals(goals: List<Goal>) {
        activeGoalsAdapter.items = goals.toMutableList()

        binding.ivEdit.isVisible = goals.isNotEmpty()
        binding.rvActiveGoals.isVisible = goals.isNotEmpty()
        binding.createFirstGoal.isVisible = goals.isEmpty()
    }

    companion object {
        fun newInstance() = AllGoalsFragment().apply {

        }
    }

}