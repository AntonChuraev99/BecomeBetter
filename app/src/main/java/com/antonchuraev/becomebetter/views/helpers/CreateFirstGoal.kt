package com.antonchuraev.becomebetter.views.helpers

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewCreateFirstGoalBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import com.antonchuraev.becomebetter.views.CustomView
import kotlin.properties.Delegates


class CreateFirstGoal @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
	: CustomView<ViewCreateFirstGoalBinding>(context , attrs , defStyleAttr) {

	var expandedState:Boolean = false
	set(value) {
		binding.llExpandedState.isVisible = value
		field = value
	}

	var style: NavigationTab? by Delegates.observable(null) {_,_,new->
		new?.let {   setViewByStyle(new) }
	}

	private fun setViewByStyle(newStyle: NavigationTab) {
		binding.tvTextInSet.text = context.getString(newStyle.createNewGoalText)

	}

	fun expandClickListener(action:()->Unit){
		binding.newGoal.setOnClickListener {
			if (!expandedState) action.invoke()
		}
	}

	fun createNewGoalClickListener(goalType: NavigationTab , action: (Goal) -> Unit){
		// TODO: 06.09.2021 create and return goal
		binding.tvStart.setOnClickListener {
			when(goalType){
				NavigationTab.MOTIVATION ->{
					action.invoke(Goal(name = binding.edName.text.toString()  , maxDaysDuration = binding.edDaysCount.text.toString().toInt() , progressType = goalType.relatedToGoalsType))
				}
				/*NavigationTab.MONEY ->{
					action.invoke(Goal(name = binding.edName.text.toString()  , maxDaysDuration = binding.edDaysCount.text.toString().toInt() , progressType = goalType.relatedToGoalsType))
				}*/

			}

			expandedState = false
		}
	}

	override fun getLayoutRes(): Int = R.layout.view_create_first_goal
}