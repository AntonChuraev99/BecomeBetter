package com.antonchuraev.becomebetter.fragments.bottomNavigation.goalsTab

import android.view.View
import androidx.core.os.bundleOf
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentGoalsTabBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import moxy.presenter.InjectPresenter


class GoalsTabFragment : BaseFragment<FragmentGoalsTabBinding>(), GoalsTabView {

    @InjectPresenter
    lateinit var presenter: GoalsTabPresenter

    override val layoutView: Int = R.layout.fragment_goals_tab


    override fun onCreateView(rootView: View) {
        initArgs()
        setListeners()
    }

    private fun setListeners() {
        binding.createGoal.apply {
            expandClickListener {
                binding.createGoal.expandedState = !binding.createGoal.expandedState
            }
            createNewGoalClickListener {
                // TODO: 06.09.2021 get goal and add to database
            }
        }
        
        binding.aboutProject.setOnClickListener {
            // TODO: 06.09.2021
        }

    }

    private fun initArgs() {
        (arguments?.getSerializable(TAB_TAG) as NavigationTab)?.let { tabStyle ->
            binding.tvTittle.text = context?.getString(tabStyle.textRes)
            binding.tvSubtittle.text = context?.getString(tabStyle.subTittleTextRes)
        }
    }

    companion object {
        val TAB_TAG = "TAB_TAG"

        fun newInstance(tab: NavigationTab) = GoalsTabFragment().apply {
            arguments = bundleOf(TAB_TAG to tab)
        }
    }

}