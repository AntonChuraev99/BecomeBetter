package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewMyGoalListItemBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import com.antonchuraev.becomebetter.helpers.extensions.setMatchWrap
import com.antonchuraev.becomebetter.views.CustomView


class GoalListItemView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CustomView<ViewMyGoalListItemBinding>(context, attrs, defStyleAttr) {

    lateinit var goal: Goal

    init {
        this.setMatchWrap()
    }


    fun setData(goal: Goal) {
        this.goal = goal

        binding.tvName.text = goal.name
        binding.tvCurrentProgress.text = goal.progress.toString()
        binding.tvMaxProgress.text = goal.progressMax.toString()

        binding.tvProjectDays.isVisible = goal.progressType == Goal.ProgressType.PERCENTS_AND_DAYS
        binding.btProjectAddDay.isVisible = goal.progressType == Goal.ProgressType.PERCENTS_AND_DAYS
        if (goal.progressType == Goal.ProgressType.PERCENTS_AND_DAYS){
            binding.tvProjectDays.text = resources.getQuantityString(R.plurals.days_no_in, goal.progressMax)

            binding.tvProjectCurrentProgress.text = goal.progressInPercentsForProject.toString()
            binding.projectProgressBar.progress = goal.progressInPercentsForProject
        }

        updateProgress()
        setStyle(goal.progressType)
    }

    private fun setStyle(progressType: Goal.ProgressType) {
        binding.llMotivationInput.isVisible = progressType == Goal.ProgressType.DAYS
        binding.llMoneyInput.isVisible = progressType == Goal.ProgressType.CUSTOM_MAX
        binding.llProjectInput.isVisible = progressType == Goal.ProgressType.PERCENTS_AND_DAYS

        binding.llProjectOutput.isVisible = progressType == Goal.ProgressType.PERCENTS_AND_DAYS

    }

    fun addListener(onAddListener:(Goal)->Unit){
        binding.btAddDay.setOnClickListener {
            onAddListener.invoke(goal.apply { progress += 1 })
            updateProgress()
        }
        binding.btRestart.setOnClickListener {
            onAddListener.invoke(goal.apply { progress = 0 })
            updateProgress()
        }

        binding.btAddCustomProgress.setOnClickListener {
            onAddListener.invoke(goal.apply { progress += binding.edCountToAdd.text.toString().toInt() })
            updateProgress()
            binding.edCountToAdd.apply {
                text.clear()
                clearFocus()
            }
        }
    }

    private fun updateProgress() {
        binding.progressBar.progress = (100F/goal.progressMax!!.toFloat() * goal.progress.toFloat()).toInt()
    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

}