package com.buntlit.picturetypeconvertor.ui

import android.os.Bundle
import com.buntlit.picturetypeconvertor.databinding.ActivityMainBinding
import com.buntlit.picturetypeconvertor.presenter.MainActivityPresenter
import com.buntlit.picturetypeconvertor.view.MainActivityView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainActivityPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}