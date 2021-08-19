package com.antonchuraev.becomebetter.base.cicerone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.likebz.toolbox.cicerone.AnimatedScreen
import ru.likebz.toolbox.cicerone.ForwardWithAdd
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.*

open class AnimatedNavigator(
    activity: FragmentActivity, fragmentManager: FragmentManager, containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> activityForward(command)
            is Replace -> activityReplace(command)
            is ForwardWithAdd -> forwardWithAdd(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack()
        }
    }

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        when (command) {
            is Forward -> {
                val screen = command.screen
                if (screen is AnimatedScreen) {
                    fragmentTransaction.setCustomAnimations(
                        screen.animEnter,
                        screen.animExit,
                        screen.popAnimEnter,
                        screen.popAnimExit
                    )
                }
            }

            is ForwardWithAdd -> {
                val screen = command.screen
                if (screen is AnimatedScreen) {
                    fragmentTransaction.setCustomAnimations(
                        screen.animEnter,
                        screen.animExit,
                        screen.popAnimEnter,
                        screen.popAnimExit
                    )
                }
            }

            is Replace -> {
                val screen = command.screen
                if (screen is AnimatedScreen) {
                    fragmentTransaction.setCustomAnimations(
                        screen.animEnter,
                        screen.animExit,
                        screen.popAnimEnter,
                        screen.popAnimExit
                    )
                }
            }
        }
    }

    private fun forwardWithAdd(command: ForwardWithAdd) {
        val screen = command.screen
        val fragment: Fragment = createFragment(screen)?: throw Exception("Fragment is Null")

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )
        fragmentTransaction.add(containerId, fragment)
            .addToBackStack(screen.screenKey).commit()
    }

    override fun activityBack() {

    }
}