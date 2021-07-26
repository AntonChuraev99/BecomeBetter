package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import moxy.presenter.InjectPresenter


class CreateGoalFromTemplatesFragment : BaseFragment<FragmentCreateGoalBinding>() ,
        CreateGoalFromTemplatesView {

    @InjectPresenter
    lateinit var presenter: CreateGoalFromTemplatesPresenter

    override val layoutView: Int = R.layout.fragment_create_goal_from_templates

    override fun onCreateView(rootView: View) {
        enableToolbar()
        presenter.loadTemplates()
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.setTittle(context?.getString(R.string.choose_template))
            toolbar.defaultBackButton(appRouter)
        }
    }

    override fun showTemplates(templates: MutableList<String>) {

    }

    companion object {

        fun newInstance() = CreateGoalFromTemplatesFragment().apply {

        }

    }

}