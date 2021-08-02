package com.antonchuraev.becomebetter.fragments.bottomNavigation

import com.antonchuraev.becomebetter.R
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

enum class NavigationTab(val code: String ,val menuId:Int) {
    ALL_GOALS("Все цели" , R.id.page_home),
    ADD_GOAL("Добавить цель" , R.id.page_add),
    PROFILE("Профиль" , R.id.page_profile);

    companion object {
        operator fun get(code: String) = values().first { it.code == code }
    }
}