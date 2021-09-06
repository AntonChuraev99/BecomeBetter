package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import android.view.View
import androidx.core.os.bundleOf
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentMainBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.BottomNavigationPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.google.android.material.tabs.TabLayoutMediator
import moxy.presenter.InjectPresenter


class BottomNavigationFragment : BaseFragment<FragmentMainBinding>() , BottomNavigationView {

    @InjectPresenter
    lateinit var presenter: BottomNavigationPresenter

    val tabsAdapter: TabsAdapter by lazy {
        TabsAdapter(this)
    }

    override val layoutView: Int = R.layout.fragment_main

    override fun onCreateView(rootView: View) {
        setupTabs()

        presenter.selectedNavigationTab = presenter.selectedNavigationTab
            ?: requireArguments().getSerializable(KEY_TAB) as NavigationTab

        binding.viewPager.currentItem = presenter.selectedNavigationTab!!.ordinal

    }

    private fun setupTabs() {
        binding.viewPager.adapter = tabsAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            NavigationTab.getByPosition(position).also { tabInfo->
                tab.text = context?.getString(tabInfo.textRes)
            }
        }.attach()
    }

    override fun openFragmentByTab(tab: NavigationTab?) {
        binding.viewPager.currentItem = tab!!.ordinal
    }


    companion object {
        private const val KEY_TAB = "tab"
        fun newInstance(tab: NavigationTab = NavigationTab.MOTIVATION) = BottomNavigationFragment().apply {
            arguments = bundleOf(KEY_TAB to tab )
        }
    }



}