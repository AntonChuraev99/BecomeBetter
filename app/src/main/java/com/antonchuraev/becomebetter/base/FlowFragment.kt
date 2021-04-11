package com.antonchuraev.becomebetter.base

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ContainerBinding
import com.antonchuraev.becomebetter.helpers.extensions.setLaunchScreen
import com.historic.app.global.navigation.FlowCiceroneViewModel
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

/**
 * класс для нескольких экранов подряд
 */
abstract class FlowFragment : BaseFragment<ContainerBinding>() {

    override val layoutView: Int = R.layout.container

    /**
     * экран
     */
    protected abstract val launchScreen: SupportAppScreen?

    private var navigatorHolder: NavigatorHolder? = null
    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment<*>

    val navigator: SupportAppNavigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun activityBack() {
                router?.finishFlow()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
                setupTransitionAnimation(command, currentFragment, nextFragment)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flowCiceroneModel = ViewModelProvider(this ).get(FlowCiceroneViewModel::class.java)

        navigatorHolder = flowCiceroneModel.getFlowCicerone(appRouter).navigatorHolder
        if (childFragmentManager.fragments.isEmpty())
            launchScreen?.let { navigator.setLaunchScreen(it) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        currentFragment
            ?.onActivityResult(requestCode, resultCode, data)
            ?: super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: router?.exit()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder?.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder?.removeNavigator()
        super.onPause()
    }

    protected fun setupTransitionAnimation(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?
    ) {
        when (command) {
            is Forward -> nextFragment?.enterTransition = Slide(Gravity.END)
        }
    }
}