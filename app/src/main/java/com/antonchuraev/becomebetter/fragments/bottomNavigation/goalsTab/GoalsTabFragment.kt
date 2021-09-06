package com.antonchuraev.becomebetter.fragments.bottomNavigation.goalsTab

import android.view.View
import androidx.core.os.bundleOf
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.databinding.FragmentGoalsTabBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsView
import moxy.presenter.InjectPresenter


class GoalsTabFragment : BaseFragment<FragmentGoalsTabBinding>() , GoalsTabView {

    @InjectPresenter
    lateinit var presenter: GoalsTabPresenter

    override val layoutView: Int = R.layout.fragment_goals_tab


    override fun onCreateView(rootView: View) {
       initArgs()
    }

    private fun initArgs() {
        (arguments?.getSerializable(TAB_TAG) as NavigationTab)?.let { tabStyle->
            //binding.textView3.text = context?.getString(tabStyle.textRes)
        }
    }

    companion object {
        val TAB_TAG = "TAB_TAG"

        fun newInstance(tab: NavigationTab) = GoalsTabFragment().apply {
            arguments = bundleOf(TAB_TAG to tab)
        }
    }

}