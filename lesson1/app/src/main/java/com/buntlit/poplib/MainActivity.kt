package com.buntlit.poplib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.buntlit.poplib.databinding.ActivityMainBinding
import com.buntlit.poplib.presenter.MainPresenter
import com.buntlit.poplib.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var bind: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)
    private var buttons: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind?.root)
        initButtons()
    }

    private fun initButtons(){
        bind?.btnCounter1?.let { buttons.add(it) }
        bind?.btnCounter2?.let { buttons.add(it) }
        bind?.btnCounter3?.let { buttons.add(it) }

        val listener = View.OnClickListener {
            presenter.counterClick(it.tag as Int)
        }

        for(index in buttons.indices){
            buttons[index].tag = index
            buttons[index].setOnClickListener(listener)
        }

    }

    override fun setButtonText(index: Int, text: String) {
        buttons[index].text = text
    }
}