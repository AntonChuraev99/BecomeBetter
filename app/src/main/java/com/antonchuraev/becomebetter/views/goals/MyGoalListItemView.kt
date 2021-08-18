package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewMyGoalListItemBinding
import com.antonchuraev.becomebetter.helpers.extensions.setMatchWrap
import com.antonchuraev.becomebetter.views.CustomView


class MyGoalListItemView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CustomView<ViewMyGoalListItemBinding>(context, attrs, defStyleAttr) {

    lateinit var goal: Goal

    init {
        this.setMatchWrap()
        setUpdateListeners()
    }

    private fun setUpdateListeners() {
        binding.progressSelector.changeListener = {

        }
    }


    fun setData(goal: Goal) {
        this.goal = goal

        binding.tvName.text = goal.name
        binding.tvProgress.text = if (goal.progressType == Goal.ProgressType.PERCENTS) "${goal.progress}%" else "${goal.progress} дней"

        binding.llProgress.isVisible = goal.progressType == Goal.ProgressType.PERCENTS
        binding.progressBar.progress = goal.progress
        binding.progressSelector.setValue(goal.progress.toFloat())

        binding.progressDaysSelector.isVisible = goal.progressType == Goal.ProgressType.DAYS
        binding.tvDaysCount.text = goal.progress.toString()
    }

    fun setSelectionMode(state: Boolean) {
        when (goal.progressType){
            Goal.ProgressType.PERCENTS->{
                binding.progressSelector.isVisible  = state
                binding.llProgress.isVisible = !state
            }
            Goal.ProgressType.DAYS->{
                binding.btIncreaseDays.isVisible = state
                binding.btMinusDays.isVisible = state
            }
        }

        binding.checkBox.isVisible = state
    }

    fun setChecked(state: Boolean) {
        binding.checkBox.isChecked = state
    }


    fun isInActiveMode(isActive: Boolean) {
        binding.flContainer.background = ContextCompat.getDrawable(
            context,
            (if (isActive) R.drawable.shape_rectangle_r_8_stroke_aston_martin else R.drawable.shape_rectangle_r_8_stroke_gray)
        )
    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

}