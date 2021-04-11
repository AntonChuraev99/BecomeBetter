package com.historic.app.global.navigation

import androidx.lifecycle.ViewModel
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class FlowCiceroneViewModel : ViewModel() {
    private var flowCicerone: Cicerone<FlowRouter>? = null

    fun getFlowCicerone(appRouter: Router): Cicerone<FlowRouter> {
        if (flowCicerone == null) {
            flowCicerone = Cicerone.create(FlowRouter(appRouter))
        }

        return flowCicerone!!
    }
}