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

enum class NavigationTab(@StringRes val textRes: Int, @StringRes val subTittleTextRes: Int , val relatedToGoalsType:Goal.ProgressType , @DrawableRes val backgroundRes:Int ) {
    MOTIVATION(R.string.motivation , R.string.motivation_subtittle , Goal.ProgressType.DAYS , R.drawable.first_background),
    MONEY(R.string.money_box , R.string.money_box_subtittle , Goal.ProgressType.CUSTOM_MAX, R.drawable.second_background),
    PROJECTS(R.string.projects , R.string.projects_subtittle , Goal.ProgressType.PERCENTS_AND_DAYS, R.drawable.third_background)

    ;

    companion object {
        fun getByPosition(pos: Int) = values().first { it.ordinal == pos} ?: throw Exception()
    }
}