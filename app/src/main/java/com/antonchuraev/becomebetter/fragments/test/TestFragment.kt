package com.antonchuraev.becomebetter.fragments.test

import android.os.Bundle
import android.view.View
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentTestBinding
import com.antonchuraev.becomebetter.fragments.splash.SplashPresenter
import com.antonchuraev.becomebetter.fragments.splash.SplashView
import moxy.presenter.InjectPresenter

class TestFragment : BaseFragment<FragmentTestBinding>() , TestView {



    @InjectPresenter
    lateinit var presenter: TestPresenter

    override val layoutView: Int = com.antonchuraev.becomebetter.R.layout.fragment_test


    override fun onCreateView(rootView: View) {

    }


    companion object {
        fun newInstance(bundle: Bundle? = null) = TestFragment().apply { arguments = bundle }
    }

    override fun openTest() {

    }

}