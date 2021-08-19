package ru.likebz.toolbox.cicerone

import ru.terrakok.cicerone.android.support.SupportAppScreen

open class AnimatedScreen(
    screen: SupportAppScreen,
    val animEnter: Int = 0,
    val animExit: Int = 0,
    val popAnimEnter: Int = 0,
    val popAnimExit: Int = 0
) : ScreenDecorator(screen)