package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
import moxy.presenter.InjectPresenter


class CreateGoalFragment : BaseFragment<FragmentCreateGoalBinding>() , CreateGoalView {

    @InjectPresenter
    lateinit var presenter: CreateGoalPresenter

    override val layoutView: Int = R.layout.fragment_create_goal

    lateinit var screenType:Mode

    override fun onCreateView(rootView: View) {
        initArgs()
        setListeners()
        enableToolbar()
    }

    private fun initArgs() {
        arguments?.let { args->
            (args.getSerializable(GOAL_TAG) as? Goal)?.let { goal: Goal ->
                binding.edName.setText(goal.name)
                binding.durationTimeSelector.setValue(goal.duration)
                binding.edDescription.setText(goal.description)
                binding.priorityTimeSelector.setValue(goal.priority)
            }

            (args.getSerializable(MODE_TAG) as? Mode)?.let { mode:Mode ->
                screenType = mode
            }
        }
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.setTittle(context?.getString(screenType.toolbarTittle))
            toolbar.defaultBackButton(appRouter)
        }
    }

    private fun setListeners() {
        binding.edName.addTextChangedListener {
            binding.edName.background = context?.getDrawable(R.drawable.shape_rectangle_r_8)
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
            binding.edName.background = context?.getDrawable(R.drawable.shape_rectangle_r_8_error)
            allFieldsChecked = false
        }
        else{
            binding.edName.background = context?.getDrawable(R.drawable.shape_rectangle_r_8)
        }

        if (allFieldsChecked){
            // TODO: 13.07.2021 save to database
        }

    }


    companion object {
        private const val GOAL_TAG = "GOAL_TAG"
        private const val MODE_TAG = "MODE_TAG"

        enum class Mode(@StringRes val toolbarTittle:Int){
            CREATE_NEW(R.string.create_goal),
            EDIT(R.string.edit_goal),
        }

        /**
         * если приходит item то редактирование или создание из шаблона
         */
        fun newInstance(item: Goal? , mode:Mode = Mode.CREATE_NEW) = CreateGoalFragment().apply {
            arguments = Bundle().apply {
                putSerializable(GOAL_TAG , item)
                putSerializable(MODE_TAG , mode)
            }
        }

    }

}