package com.antonchuraev.becomebetter.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.cicerone.AnimatedNavigator
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder

class MainActivity:MvpAppCompatActivity() , MvpView
{
    @InjectPresenter
    lateinit var presenter: AppPresenter

    private val navigatorHolder: NavigatorHolder by inject()

    /*private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
            private var doubleBackToExitPressedOnce: Boolean = false

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }

            override fun activityBack() {
                if (doubleBackToExitPressedOnce) {
                    super.activityBack()
                } else {
                    doubleBackToExitPressedOnce = true
                }
            }
        }
    }*/

    private val animatedNavigator: AnimatedNavigator =
        object : AnimatedNavigator(this, supportFragmentManager, R.id.container) {}

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment<*>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO: 10.04.2021 проверка стартового экрана
        presenter.onAppStartOpenScreen()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                loge("Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            loge("fcm token:$token")
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        currentFragment
            ?.onActivityResult(requestCode, resultCode, data)
            ?: super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(animatedNavigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun loge(text:Any){
        Log.e("tag", "log:$text")
    }

}