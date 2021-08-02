package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewMyGoalListItemBinding
import com.antonchuraev.becomebetter.helpers.extensions.setMatchWrap
import com.antonchuraev.becomebetter.views.CustomView


class MyGoalListItemView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0 ): CustomView<ViewMyGoalListItemBinding>(context, attrs, defStyleAttr) {

    init {
        this.setMatchWrap()

         binding.llContainer.setOnClickListener {
            binding.checkBox.isChecked = !binding.checkBox.isChecked
        }
    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

    fun setData(goal: Goal){
        binding.tvName.text = goal.name
        binding.progressBar.progress = goal.progress
    }

}