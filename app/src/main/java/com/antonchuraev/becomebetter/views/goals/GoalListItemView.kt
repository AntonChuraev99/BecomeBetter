package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewMyGoalListItemBinding
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
        binding.tvCurrentProgress.text = goal.currentDayDuration.toString()
        binding.tvMaxProgress.text = goal.maxDaysDuration.toString()

        updateProgress()
    }

    fun addDayListener(onAddListener:(Goal)->Unit){
        binding.btAddDay.setOnClickListener {
            onAddListener.invoke(goal.apply { currentDayDuration += 1 })
            updateProgress()
        }
    }

    private fun updateProgress() {
        binding.progressBar.progress = (100F/goal.maxDaysDuration!!.toFloat() * goal.currentDayDuration.toFloat()).toInt()
    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

}