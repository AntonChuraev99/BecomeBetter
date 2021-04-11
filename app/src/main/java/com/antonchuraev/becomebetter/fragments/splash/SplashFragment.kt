package com.antonchuraev.becomebetter.fragments.splash

import android.view.View
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.databinding.FragmentSplashBinding
import moxy.presenter.InjectPresenter


class SplashFragment : BaseFragment<FragmentSplashBinding>() , SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override val layoutView: Int = com.antonchuraev.becomebetter.R.layout.fragment_splash


    override fun onCreateView(rootView: View) {

        setListeners()



    }

    private fun setListeners() {
        binding.openTest.setOnClickListener {
            openTestFragment()
        }

        binding.openMainMenu.setOnClickListener {
            openMainMenu()
        }


    }

    override fun openMainMenu() {
        router?.newRootScreen( Screens.Screen.BottomNavigation() )
    }

    override fun openTestFragment() {
        router?.navigateTo( Screens.Test.Test() )
    }


    companion object {
        fun newInstance() = SplashFragment()
    }

}