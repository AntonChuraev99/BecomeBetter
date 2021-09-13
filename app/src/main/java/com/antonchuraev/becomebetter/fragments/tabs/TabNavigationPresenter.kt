package com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.fragments.tabs.TabNavigationView
import moxy.InjectViewState
import java.lang.Exception
import kotlin.properties.Delegates

@InjectViewState
class TabNavigationPresenter: BasePresenter<TabNavigationView>() {

    var selectedNavigationTab by Delegates.observable<NavigationTab?>(NavigationTab.MOTIVATION) { _, old, new ->
        if (new != old)
            viewState.openFragmentByTab(new)
    }


}

enum class NavigationTab(@StringRes val textRes: Int, @StringRes val subTittleTextRes: Int , val relatedToGoalsType:Goal.ProgressType , @DrawableRes val backgroundRes:Int , @StringRes val createNewGoalText:Int , @StringRes val editTextHint:Int ,val editMaxLength:Int) {


    MOTIVATION(R.string.motivation , R.string.motivation_subtittle , Goal.ProgressType.DAYS , R.drawable.first_background , R.string.create_goal_motivation_text , R.string.motivation_hint , NavigationTab.MOTIVATION_MAX_INPUT_LENGTH),
    MONEY(R.string.money_box , R.string.money_box_subtittle , Goal.ProgressType.CUSTOM_MAX, R.drawable.second_background ,  R.string.create_goal_money_text , R.string.money_hint, NavigationTab.OTHER_MAX_INPUT_LENGTH),
    PROJECTS(R.string.projects , R.string.projects_subtittle , Goal.ProgressType.PERCENTS_AND_DAYS, R.drawable.third_background , R.string.todo , R.string.todo, NavigationTab.OTHER_MAX_INPUT_LENGTH)

    ;


    companion object {
        const val MOTIVATION_MAX_INPUT_LENGTH = 3
        const val OTHER_MAX_INPUT_LENGTH = 10

        fun getByPosition(pos: Int) = values().first { it.ordinal == pos} ?: throw Exception()
    }

}