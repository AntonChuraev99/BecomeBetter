package com.antonchuraev.becomebetter.views.goals

import android.content.Context
import android.util.AttributeSet
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

    init {
        this.setMatchWrap()

    }

    override fun getLayoutRes() = R.layout.view_my_goal_list_item

    fun setData(goal: Goal) {
        binding.tvName.text = goal.name
        binding.progressBar.progress = goal.progress
        binding.tvProgress.text = "${goal.progress}%"
    }

    fun setSelectionMode(state: Boolean) {
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

}