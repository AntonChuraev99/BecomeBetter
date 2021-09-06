package com.antonchuraev.becomebetter.fragments.bottomNavigation

import androidx.annotation.StringRes
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.BottomNavigationView
import moxy.InjectViewState
import java.lang.Exception
import kotlin.properties.Delegates

@InjectViewState
class BottomNavigationPresenter: BasePresenter<BottomNavigationView>() {

    var selectedNavigationTab by Delegates.observable<NavigationTab?>(NavigationTab.MOTIVATION) { _, old, new ->
        if (new != old)
            viewState.openFragmentByTab(new)
    }


}

enum class NavigationTab(@StringRes val textRes: Int, @StringRes val subTittleTextRes: Int  ) {
    MOTIVATION(R.string.motivation , R.string.motivation_subtittle),
    MONEY(R.string.money_box , R.string.money_box_subtittle),
    PROJECTS(R.string.projects , R.string.projects_subtittle)

    ;

    companion object {
        fun getByPosition(pos: Int) = values().first { it.ordinal == pos} ?: throw Exception()
    }
}