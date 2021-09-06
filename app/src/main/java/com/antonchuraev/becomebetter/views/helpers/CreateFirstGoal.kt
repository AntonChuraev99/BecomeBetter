package com.antonchuraev.becomebetter.views.helpers

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.databinding.ViewCreateFirstGoalBinding
import com.antonchuraev.becomebetter.databinding.ViewCustomToolbarBinding
import com.antonchuraev.becomebetter.helpers.extensions.gone
import com.antonchuraev.becomebetter.helpers.extensions.show
import com.antonchuraev.becomebetter.views.CustomView
import com.historic.app.global.navigation.FlowRouter
import ru.terrakok.cicerone.Router


class CreateFirstGoal @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
	: CustomView<ViewCreateFirstGoalBinding>(context , attrs , defStyleAttr) {

	var expandedState:Boolean = false
	set(value) {
		binding.llExpandedState.isVisible = value
		field = value
	}


	fun expandClickListener(action:()->Unit){
		binding.newGoal.setOnClickListener {
			if (!expandedState) action.invoke()
		}
	}

	fun createNewGoalClickListener(action:()->Unit){
		// TODO: 06.09.2021 create and return goal
		binding.tvStart.setOnClickListener {
			action.invoke()
			expandedState = false
		}
	}

	override fun getLayoutRes(): Int = R.layout.view_create_first_goal
}