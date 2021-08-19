package ru.likebz.toolbox.cicerone

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CommonRouter : Router() {
    fun navigateToWithAdd(screen: SupportAppScreen) {
        executeCommands(ForwardWithAdd(screen))
    }
}