package com.antonchuraev.becomebetter.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.antonchuraev.becomebetter.views.toolbar.CustomToolbar
import com.historic.app.global.navigation.FlowCiceroneViewModel
import com.historic.app.global.navigation.FlowRouter
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router

abstract class BaseFragment<T : ViewDataBinding>:MvpAppCompatFragment()
{
    var toolbar:CustomToolbar? = null

    val appRouter: Router by inject()

    protected val router: FlowRouter? by lazy {
        val flowParent = this as? FlowFragment ?: getParent(this)

        flowParent?.let {
            ViewModelProvider(it)
                .get(FlowCiceroneViewModel::class.java)
                .getFlowCicerone(appRouter).router

        }

    }

    private fun getParent(fragment: Fragment): FlowFragment? {
        return when {
            fragment is FlowFragment -> fragment
            fragment.parentFragment == null -> null
            else -> getParent(fragment.requireParentFragment())
        }
    }

    @get:LayoutRes
    protected abstract val layoutView: Int

    protected lateinit var binding: T

    /**
     * сделано final для удобства
     */
    final override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container!!)
        toolbar = (activity as MainActivity).toolbar
        toolbar?.clearData()
        val root = binding.root
        onCreateView(root)
        return root
    }

    abstract fun onCreateView(rootView: View)


    private fun initBinding(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, layoutView, container, false)
    }

    open fun onBackPressed() {
        router?.exit()
    }

    fun <D> log(text:D){
        Log.e(this.javaClass.simpleName, "${text.toString()}");
    }

}


