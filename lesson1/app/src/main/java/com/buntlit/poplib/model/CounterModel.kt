package com.buntlit.poplib.model

class CounterModel {
    private val counters = mutableListOf(0,0,0)

    private fun getCurrent(index: Int): Int {
        return counters[index]
    }

    private fun set(index: Int, value: Int){
        counters[index] = value
    }

    fun next(index: Int): Int{
        val nextValue = getCurrent(index) + 1
        set(index, nextValue)
        return nextValue
    }

}