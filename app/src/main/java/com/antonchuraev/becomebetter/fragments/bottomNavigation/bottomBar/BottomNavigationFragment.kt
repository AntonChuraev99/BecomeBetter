package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentMainBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.BottomNavigationPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.AddGoalFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.AddGoalView
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsFragment
import com.antonchuraev.becomebetter.fragments.test.TestFragment
import moxy.presenter.InjectPresenter


class BottomNavigationFragment : BaseFragment<FragmentMainBinding>() , BottomNavigationView {

    @InjectPresenter
    lateinit var presenter: BottomNavigationPresenter

    private val tabMenus: List<NavigationTab>
        get() = listOf(
                NavigationTab.ALL_GOALS,
                NavigationTab.ADD_GOAL,
                NavigationTab.PROFILE
        )

    private val tabFragments: Map<NavigationTab, Fragment> by lazy {
        mapOf(
                NavigationTab.ALL_GOALS to getFragmentByTag(
                        childFragmentManager,
                        NavigationTab.ALL_GOALS.code, AllGoalsFragment.newInstance()
                ),
                NavigationTab.ADD_GOAL to getFragmentByTag(
                        childFragmentManager,
                        NavigationTab.ADD_GOAL.code, AddGoalFragment.newInstance()
                ),
                NavigationTab.PROFILE to getFragmentByTag(
                        childFragmentManager,
                        NavigationTab.PROFILE.code, TestFragment.newInstance()
                )
        )
    }

    override val layoutView: Int = R.layout.fragment_main




    override fun onCreateView(rootView: View) {
        setBottomListener()
        presenter.selectedNavigationTab = presenter.selectedNavigationTab
                ?: NavigationTab[requireArguments().getString(KEY_TAB)!!]

    }

    private fun setBottomListener() {

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_home -> {

                    openFragmentByTab(NavigationTab.ALL_GOALS)

                    true
                }
                R.id.page_add -> {

                    openFragmentByTab(NavigationTab.ADD_GOAL)

                    true
                }
                R.id.page_profile -> {
                    openFragmentByTab(NavigationTab.PROFILE)
                    true
                }
                else -> false
            }
        }


    }


    private fun getFragmentByTag(
            fm: FragmentManager,
            tag: String,
            defaultFragment: Fragment
    ): Fragment {
        return fm.findFragmentByTag(tag) ?: defaultFragment.also {
            fm.commitNow {
                add(R.id.fragmentContainer, it, tag)
                detach(it)
            }
        }
    }

    override fun openFragmentByTab(tab: NavigationTab?) {

        childFragmentManager.commitNow {
            tabFragments.forEach {
                if (it.key == tab)
                    attach(it.value)
                else
                    detach(it.value)
            }
        }

    }

    companion object {
        private const val KEY_TAB = "tab"
        fun newInstance(tab: NavigationTab = NavigationTab.ALL_GOALS) = BottomNavigationFragment().apply {
            arguments = bundleOf(KEY_TAB to tab.code)
        }
    }

}