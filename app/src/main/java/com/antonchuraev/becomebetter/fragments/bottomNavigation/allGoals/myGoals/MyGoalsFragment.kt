package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentMyGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.helpers.adapters.GoalsAdapter
import com.antonchuraev.becomebetter.helpers.extensions.add10DpDecorator
import com.antonchuraev.becomebetter.views.goals.MyGoalListItemView
import moxy.presenter.InjectPresenter


class MyGoalsFragment : BaseFragment<FragmentMyGoalsBinding>(), MyGoalsView {

    @InjectPresenter
    lateinit var presenter: MyGoalsGoalsPresenter

    override val layoutView: Int = R.layout.fragment_my_goals

    val activeGoalsAdapter = GoalsAdapter().apply {
        onItemClickListener = { item ->
            appRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item , mode = CreateGoalFragment.Companion.Mode.EDIT))
        }
    }

    val disabledGoalsAdapter = GoalsAdapter().apply {
        isActiveMode = false
        onItemClickListener = { item ->
            appRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item ,mode = CreateGoalFragment.Companion.Mode.EDIT))
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
    }

    private fun chekAdaptersChangeVisibility() {
        activeGoalsAdapter.notifyDataSetChanged()
        disabledGoalsAdapter.notifyDataSetChanged()

        binding.llActiveGoals.isVisible = activeGoalsAdapter.items.isNotEmpty()
        binding.llDisabledGoals.isVisible = disabledGoalsAdapter.items.isNotEmpty()
    }

    override fun showActiveGoals(templates: MutableList<Goal>) {
        activeGoalsAdapter.items = templates
    }

    override fun showDisabledGoals(goals: MutableList<Goal>) {
        disabledGoalsAdapter.items = goals
        binding.llDisabledGoals.isVisible = goals.isNotEmpty()
    }



    companion object {
        fun newInstance() = MyGoalsFragment().apply {

        }
    }


}