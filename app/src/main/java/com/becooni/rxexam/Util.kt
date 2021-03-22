package com.becooni.rxexam

import android.util.Log


fun log(text: String) {
    Log.e("RXTEST", "${getThreadName()} | $text")
}

fun getThreadName(): String {
    val threadName = Thread.currentThread().name
    if (threadName.length > 30) {
        return threadName.substring(0, 30) + "..."
    }
    return threadName
}