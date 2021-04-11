package com.antonchuraev.becomebetter.fragments.bottomNavigation

import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.BottomNavigationView
import moxy.InjectViewState
import kotlin.properties.Delegates

@InjectViewState
class BottomNavigationPresenter: BasePresenter<BottomNavigationView>() {

    var selectedNavigationTab by Delegates.observable<NavigationTab?>(null) { _, old, new ->
        if (new != old)
            viewState.openFragmentByTab(new)
    }


}

enum class NavigationTab(val code: String) {
    ALL_GOALS("Все цели"),
    ADD_GOAL("Добавить цель"),
    PROFILE("Профиль");

    companion object {
        operator fun get(code: String) = values().first { it.code == code }
    }
}