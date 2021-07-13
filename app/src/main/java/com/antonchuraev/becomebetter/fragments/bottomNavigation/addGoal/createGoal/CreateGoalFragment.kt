package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
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

        binding.btCreate.onClickListener {
            checkFields()
        }
    }

    private fun checkFields() {
        var allFieldsChecked = true

        if (binding.edName.text.toString().isBlank()){
            // TODO: 13.07.2021 error view
            allFieldsChecked = false
        }

        if (allFieldsChecked){
            // TODO: 13.07.2021 save to database
        }

    }


    companion object {

        fun newInstance() = CreateGoalFragment().apply {

        }

    }

}