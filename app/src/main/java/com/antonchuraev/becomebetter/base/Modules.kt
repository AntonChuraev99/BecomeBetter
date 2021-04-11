package com.antonchuraev.becomebetter.base

import com.antonchuraev.becomebetter.base.koin.appModule
import com.antonchuraev.becomebetter.base.koin.navigationModule

val appComponent = listOf(
    appModule,
    navigationModule,

)