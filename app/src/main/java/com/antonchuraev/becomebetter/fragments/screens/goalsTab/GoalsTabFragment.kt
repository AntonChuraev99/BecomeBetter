package com.antonchuraev.becomebetter.fragments.screens.goalsTab

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentGoalsTabBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import com.antonchuraev.becomebetter.helpers.adapters.GoalsAdapter
import com.antonchuraev.becomebetter.helpers.extensions.add10DpDecorator
import moxy.presenter.InjectPresenter
import kotlin.properties.Delegates


class GoalsTabFragment : BaseFragment<FragmentGoalsTabBinding>(), GoalsTabView {

    @InjectPresenter
    lateinit var presenter: GoalsTabPresenter

    override val layoutView: Int = R.layout.fragment_goals_tab

    val goalsAdapter = GoalsAdapter().apply {
        onItemUpdateListener = {
            presenter.updateGoal(it , requireContext())
        }
    }

    var goalsType: NavigationTab? by Delegates.observable(null){ _, _, new->
        new?.let { updateScreenType(it) }
    }

    override fun onCreateView(rootView: View) {
        initRv()
        initArgs()
        setListeners()
    }

    private fun initRv() {
        binding.rvGoals.apply {
            layoutManager = LinearLayoutManager(context)
            add10DpDecorator(context , DividerItemDecoration.VERTICAL)
            adapter = goalsAdapter
        }
    }


    private fun initArgs() {
        (arguments?.getSerializable(TAB_TAG) as NavigationTab)?.let { tabStyle ->
            binding.tvTittle.text = context?.getString(tabStyle.textRes)
            binding.tvSubtittle.text = context?.getString(tabStyle.subTittleTextRes)
            binding.background.background = ContextCompat.getDrawable(requireContext() , tabStyle.backgroundRes)

            goalsType = tabStyle
        }
    }

    private fun setListeners() {
        binding.createGoal.apply {
            expandClickListener {
                binding.createGoal.expandedState = !binding.createGoal.expandedState
            }
            createNewGoalClickListener(goalsType!!) {
                presenter.addGoal(it , context)
            }
        }

        binding.aboutProject.setOnClickListener {
            // TODO: 06.09.2021
        }

    }

    private fun updateScreenType(screenType: NavigationTab) {
        presenter.loadGoals( requireContext() , screenType)
    }

    override fun showGoals(goals: List<Goal>) {
        goalsAdapter.items = goals.toMutableList()
    }

    override fun reloadGoals() {
        presenter.loadGoals(requireContext() , goalsType!!)
    }

    companion object {
        val TAB_TAG = "TAB_TAG"

        fun newInstance(tab: NavigationTab) = GoalsTabFragment().apply {
            arguments = bundleOf(TAB_TAG to tab)
        }
    }

}