package com.antonchuraev.becomebetter.base.koin

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

internal val navigationModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()
    single { cicerone.router }
    single { cicerone.navigatorHolder }
}