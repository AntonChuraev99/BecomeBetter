package com.antonchuraev.becomebetter.base.koin

import org.koin.dsl.module
import ru.likebz.toolbox.cicerone.CommonRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

internal val navigationModule = module {
    val router = CommonRouter()
    val cicerone: Cicerone<CommonRouter> = Cicerone.create(router)
    single { cicerone.router }
    single { cicerone.navigatorHolder }
}