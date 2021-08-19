package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.cicerone.Screens
import com.antonchuraev.becomebetter.databinding.FragmentAddGoalBinding
import moxy.presenter.InjectPresenter


class AddGoalFragment : BaseFragment<FragmentAddGoalBinding>() , AddGoalView {

    @InjectPresenter
    lateinit var presenter: AddGoalPresenter

    override val layoutView: Int = R.layout.fragment_add_goal

    override fun onCreateView(rootView: View) {
        setListeners()
    }

    private fun setListeners() {
        binding.addGoalContainer.setOnClickListener {
            presenter.toCreateYourGoalPressed()
        }

        binding.createGoalFromAssetsContainer.setOnClickListener {
            presenter.toCreateGoalFromTemplatesPressed()
        }
    }


    override fun toCreateYourGoal() {
        getRouter().navigateTo(Screens.Screen.AddGoals.CreateGoal())
    }

    override fun toCreateGoalFromTemplates() {
        getRouter().navigateTo(Screens.Screen.AddGoals.CreateGoalFromTemplate())
    }

    companion object {

        fun newInstance() = AddGoalFragment().apply {

        }

    }

}