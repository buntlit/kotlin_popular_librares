package com.buntlit.githubclient.ui

import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.ActivityMainBinding
import com.buntlit.githubclient.mvp.presenter.MainPresenter
import com.buntlit.githubclient.mvp.view.MainView
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(App.INSTANCE.router) }
    private val navigatorHolder = App.INSTANCE.navigatorHolder
    private lateinit var navigator : Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navigator = AppNavigator(this, binding?.container?.id!!)
        setContentView(binding?.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                onBack()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                       onBack()
                    }
                })
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onBack() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed())
                return
        }
        presenter.backClicked()
    }
}