package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import android.util.Log
import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentAddGoalBinding
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import moxy.presenter.InjectPresenter


class AddGoalFragment : BaseFragment<FragmentAddGoalBinding>() , AddGoalView {

    @InjectPresenter
    lateinit var presenter: AddGoalPresenter

    override val layoutView: Int = R.layout.fragment_add_goal

    override fun onCreateView(rootView: View) {
        Log.e("tag" , "onCreateView $toolbar");
        toolbar?.setTittle("AddGoalFragment")
    }




    companion object {

        fun newInstance() = AddGoalFragment().apply {

        }
    }

}