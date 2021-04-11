package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewGoalInListBinding
import com.antonchuraev.becomebetter.helpers.extensions.toast
import com.antonchuraev.becomebetter.views.CustomView


class GoalInListView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0 ): CustomView<ViewGoalInListBinding>(context, attrs, defStyleAttr , R.layout.view_goal_in_list) {
    //override val layoutRes: Int = R.layout.view_goal_in_list


    fun setData(goal:Goal){
        context.toast("setData:${goal}")
        binding.name.text = goal.name
    }




}