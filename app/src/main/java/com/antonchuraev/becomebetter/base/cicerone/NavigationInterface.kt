package ru.likebz.toolbox.cicerone

import ru.terrakok.cicerone.android.support.SupportAppScreen

interface NavigationInterface {

    fun newRootScreen(screen: SupportAppScreen)

    fun openNeighborScreen(screen: SupportAppScreen)

    fun openFromBottomScreen(screen: SupportAppScreen)

    fun openFadeScreen(screen: SupportAppScreen)

    fun navigateTo(screen: SupportAppScreen)

    fun insertScreen(screen: SupportAppScreen)

    fun backTo(screen: SupportAppScreen)

    fun exit()
}