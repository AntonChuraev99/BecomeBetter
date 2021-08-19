package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.cicerone.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentMyGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.helpers.adapters.GoalsAdapter
import com.antonchuraev.becomebetter.helpers.extensions.add10DpDecorator
import moxy.presenter.InjectPresenter


class MyGoalsFragment : BaseFragment<FragmentMyGoalsBinding>(), MyGoalsView {

    @InjectPresenter
    lateinit var presenter: MyGoalsGoalsPresenter

    override val layoutView: Int = R.layout.fragment_my_goals

    val activeGoalsAdapter = GoalsAdapter().apply {
        onItemClickListener = { item ->
            appMainRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item , mode = CreateGoalFragment.Companion.Mode.EDIT))
        }
        onItemUpdateListener = {
            presenter.updateGoal(it , requireContext())
        }
    }

    val disabledGoalsAdapter = GoalsAdapter().apply {
        isActiveMode = false
        onItemClickListener = { item ->
            appMainRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item ,mode = CreateGoalFragment.Companion.Mode.EDIT))
        }
        onItemUpdateListener = {
            presenter.updateGoal(it , requireContext())
        }
    }

    override fun onCreateView(rootView: View) {
        enableToolbar()
        setListeners()
        setRv()
        presenter.loadActiveGoals(requireContext())
        presenter.loadDisabledGoals(requireContext())
    }

    private fun setRv() {
        binding.rvActiveGoals.apply {
            layoutManager = LinearLayoutManager(context)
            add10DpDecorator(requireContext() , DividerItemDecoration.VERTICAL)
            adapter = activeGoalsAdapter
        }
        binding.rvDisabledGoals.apply {
            layoutManager = LinearLayoutManager(context)
            add10DpDecorator(requireContext() , DividerItemDecoration.VERTICAL)
            adapter = disabledGoalsAdapter
        }
    }

    private fun enableToolbar() {
        setDefaultToolbar(R.string.all_my_goals)
        toolbar?.setRightButton(R.drawable.ic_edit) {
            activeGoalsAdapter.isSelectionEnabled = !activeGoalsAdapter.isSelectionEnabled
            disabledGoalsAdapter.isSelectionEnabled = !disabledGoalsAdapter.isSelectionEnabled
            if (activeGoalsAdapter.isSelectionEnabled) toolbar?.setRightButton(R.drawable.ic_apply) else toolbar?.setRightButton(R.drawable.ic_edit)

            chekAdaptersChangeVisibility()
        }
    }

    private fun setListeners() {
        activeGoalsAdapter.onItemSelectionChange = { item,state ->
            if (!state){
                presenter.updateGoal(item , requireContext())
                activeGoalsAdapter.items.remove(item)
                disabledGoalsAdapter.items.add(item)
                chekAdaptersChangeVisibility()
            }
        }
        disabledGoalsAdapter.onItemSelectionChange = { item,state ->
            if (state){
                presenter.updateGoal(item , requireContext())
                disabledGoalsAdapter.items.remove(item)
                activeGoalsAdapter.items.add(item)
                chekAdaptersChangeVisibility()
            }

        }

        binding.createFirstGoal.setOnClickListener {
            appMainRouter.replaceScreen ( Screens.Screen.BottomNavigation( NavigationTab.ADD_GOAL ) )
        }
    }

    private fun chekAdaptersChangeVisibility() {
        activeGoalsAdapter.notifyDataSetChanged()
        disabledGoalsAdapter.notifyDataSetChanged()

        binding.llActiveGoals.isVisible = activeGoalsAdapter.items.isNotEmpty()
        binding.llDisabledGoals.isVisible = disabledGoalsAdapter.items.isNotEmpty()
    }

    override fun showActiveGoals(goals: MutableList<Goal>) {
        activeGoalsAdapter.items = goals
        binding.llActiveGoals.isVisible = goals.isNotEmpty()

        binding.createFirstGoal.isVisible = activeGoalsAdapter.items.isEmpty() && disabledGoalsAdapter.items.isEmpty()
    }

    override fun showDisabledGoals(goals: MutableList<Goal>) {
        disabledGoalsAdapter.items = goals
        binding.llDisabledGoals.isVisible = goals.isNotEmpty()

        binding.createFirstGoal.isVisible = activeGoalsAdapter.items.isEmpty() && disabledGoalsAdapter.items.isEmpty()
    }



    companion object {
        fun newInstance() = MyGoalsFragment().apply {

        }
    }


}