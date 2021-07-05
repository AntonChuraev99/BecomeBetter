package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal

import android.view.View
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentAddGoalBinding
import com.antonchuraev.becomebetter.helpers.extensions.gone
import com.antonchuraev.becomebetter.helpers.extensions.show
import moxy.presenter.InjectPresenter


class AddGoalFragment : BaseFragment<FragmentAddGoalBinding>() , AddGoalView {

    @InjectPresenter
    lateinit var presenter: AddGoalPresenter

    override val layoutView: Int = R.layout.fragment_add_goal

    override fun onCreateView(rootView: View) {

        setListeners()
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.defaultBackButton(router)
        }
    }

    private fun setListeners() {

        binding.addGoalContainer.setOnClickListener {
            presenter.createYourGoalPressed()
        }
    }


    override fun showCreateYourGoal() {
        binding.llChooseWhatCreate.gone()
        binding.llCreate.show()

        enableToolbar()
    }

    companion object {

        fun newInstance() = AddGoalFragment().apply {

        }

    }

}