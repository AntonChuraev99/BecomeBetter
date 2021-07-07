package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentAddGoalBinding
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.AddGoalPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.AddGoalView
import com.antonchuraev.becomebetter.helpers.extensions.gone
import com.antonchuraev.becomebetter.helpers.extensions.show
import moxy.presenter.InjectPresenter


class CreateGoalFragment : BaseFragment<FragmentCreateGoalBinding>() , CreateGoalView {

    @InjectPresenter
    lateinit var presenter: CreateGoalPresenter

    override val layoutView: Int = R.layout.fragment_create_goal

    override fun onCreateView(rootView: View) {

        setListeners()
        enableToolbar()
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.setTittle(context?.getString(R.string.create_goal))
            toolbar.defaultBackButton(appRouter)
        }
    }

    private fun setListeners() {
        binding.edName.addTextChangedListener {
            binding.btCreate.setState(it.toString().isNotBlank())
        }
    }


    companion object {

        fun newInstance() = CreateGoalFragment().apply {

        }

    }

}