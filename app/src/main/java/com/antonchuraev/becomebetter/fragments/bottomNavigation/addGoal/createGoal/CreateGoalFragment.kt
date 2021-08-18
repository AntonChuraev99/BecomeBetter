package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.annotation.StringRes
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
import com.antonchuraev.becomebetter.views.utils.ButtonView
import moxy.presenter.InjectPresenter


class CreateGoalFragment : BaseFragment<FragmentCreateGoalBinding>() , CreateGoalView {

    @InjectPresenter
    lateinit var presenter: CreateGoalPresenter

    override val layoutView: Int = R.layout.fragment_create_goal

    lateinit var editedGoal: Goal

    lateinit var screenType:Mode

    override fun onCreateView(rootView: View) {
        initArgs()
        setScreenType()
        setData()
        refreshButton()
        setListeners()
        enableToolbar()
    }

    private fun setData() {

    }

    private fun setScreenType() {
        binding.btCreate.style = screenType.buttonStyle
        binding.btDelete.isVisible = screenType == Mode.EDIT
    }

    private fun initArgs() {
        arguments?.let { args->
            (args.getSerializable(GOAL_TAG) as? Goal)?.let { goal: Goal ->
                editedGoal = goal
                binding.edName.setText(goal.name)
                binding.durationTimeSelector.setValue(goal.duration)
                binding.edDescription.setText(goal.description)
                binding.priorityTimeSelector.setValue(goal.priority)
                binding.progressSelector.setValue(goal.progress.toFloat())
                binding.progressTypeSelector.setSelection(goal.progressType.ordinal)
                setProgressTypeVisible(goal.progressType.ordinal)
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
            refreshButton()
            binding.edName.background = context?.getDrawable(R.drawable.shape_rectangle_r_8)
        }

        binding.btCreate.onClickListener {
            checkFields()
        }

        binding.progressTypeSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, selectedItemPosition: Int, p3: Long) {
                setProgressTypeVisible(selectedItemPosition)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btMinusDays.setOnClickListener { changeDays(false) }
        binding.btIncreaseDays.setOnClickListener { changeDays(true) }
    }

    private fun changeDays(action: Boolean) {
        val currentNumber = binding.tvDaysCount.text.trim().toString().toInt()
        if (action){
            binding.tvDaysCount.text = (currentNumber+1).toString()
        }
        else{
            //check zero
            if (currentNumber>0){
                binding.tvDaysCount.text = (currentNumber-1).toString()
            }
        }
    }

    private fun setProgressTypeVisible(ordinal: Int) {
        binding.progressSelector.isVisible = ordinal == 0
        binding.progressDaysSelector.isVisible = ordinal == 1
    }

    private fun refreshButton() {
        binding.btCreate.setActiveState(binding.edName.text.toString().isNotBlank())
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
            when(screenType){
                Mode.EDIT->editGoal()
                Mode.CREATE_NEW->createGoal()
            }
        }

    }

    private fun editGoal() {
        editedGoal.name = binding.edName.text.toString()
        editedGoal.duration = binding.durationTimeSelector.getValue()
        editedGoal.description = binding.edDescription.text.toString()
        editedGoal.priority = binding.priorityTimeSelector.getValue()
        editedGoal.progressType = if (binding.progressTypeSelector.selectedItemPosition==0) Goal.ProgressType.PERCENTS else Goal.ProgressType.DAYS
        editedGoal.progress = if (binding.progressTypeSelector.selectedItemPosition==0) binding.progressSelector.getValue().toInt() else binding.tvDaysCount.text.toString().toInt()

        presenter.updateGoal(editedGoal , requireContext())
    }

    private fun createGoal() {
        val goal = Goal(
            name = binding.edName.text.toString() ,
            duration = binding.durationTimeSelector.getValue(),
            description = binding.edDescription.text.toString(),
            priority = binding.priorityTimeSelector.getValue(),
            progressType = if (binding.progressTypeSelector.selectedItemPosition==0) Goal.ProgressType.PERCENTS else Goal.ProgressType.DAYS,
            progress = if (binding.progressTypeSelector.selectedItemPosition==0) binding.progressSelector.getValue().toInt() else binding.tvDaysCount.text.toString().toInt() ,
            isActive = true
        )

        presenter.createGoal(goal , requireContext())
    }

    override fun finishWork() {
        appRouter.exit()
    }

    companion object {
        private const val GOAL_TAG = "GOAL_TAG"
        private const val MODE_TAG = "MODE_TAG"

        enum class Mode(@StringRes val toolbarTittle:Int , val buttonStyle :ButtonView.Type){
            CREATE_NEW(R.string.create_goal , ButtonView.Type.DISABLE),
            EDIT(R.string.edit_goal , ButtonView.Type.SAVE),
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