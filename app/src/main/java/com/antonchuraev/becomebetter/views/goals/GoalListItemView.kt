package com.antonchuraev.becomebetter.views.goals

import android.content.Context
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

        updateProgress()
        setStyle(goal.progressType)
    }

    private fun setStyle(progressType: Goal.ProgressType) {
        binding.llMotivationInput.isVisible = progressType == Goal.ProgressType.DAYS
        binding.llMoneyInput.isVisible = progressType == Goal.ProgressType.CUSTOM_MAX
    }

    fun addDayListener(onAddListener:(Goal)->Unit){
        binding.btAddDay.setOnClickListener {
            onAddListener.invoke(goal.apply { progress += 1 })
            updateProgress()
        }
    }

    private fun updateProgress() {
        binding.progressBar.progress = (100F/goal.progressMax!!.toFloat() * goal.progress.toFloat()).toInt()
    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

}