package com.becooni.rxexam

import android.util.Log
import kotlin.random.Random

fun log(text: String) {
    val time = System.currentTimeMillis() - startTime
    Log.e("RXTEST", "${getThreadName()} | $time | $text")
}

fun getThreadName(): String {
    val threadName = Thread.currentThread().name
    if (threadName.length > 30) {
        return threadName.substring(0, 30) + "..."
    }
    return threadName
}

fun start() {
    startTime = System.currentTimeMillis()
}

private var startTime = 0L

fun doSomething() {
    Thread.sleep(Random.nextLong(100))
}