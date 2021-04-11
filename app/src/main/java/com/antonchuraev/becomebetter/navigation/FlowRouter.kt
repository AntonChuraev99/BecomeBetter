package com.historic.app.global.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

// FlowFragment-wide local router
class FlowRouter(private val appRouter: Router) : Router() {

    fun startFlow(flows: SupportAppScreen) {
        appRouter.navigateTo(flows)
    }

    fun replaceFlow(flow: SupportAppScreen) {
        appRouter.replaceScreen(flow)
    }

    fun newRootFlow(flows: SupportAppScreen) {
        appRouter.newRootScreen(flows)
    }

    fun newRootFlowChain(vararg flows: SupportAppScreen) {
        appRouter.newRootChain(*flows)
    }

    fun finishFlow() {
        appRouter.exit()
    }
}