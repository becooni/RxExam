package com.becooni.rxexam.operator.create

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun interval() {
    val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map { (it + 1) * 100 }
        .take(5)

    source.subscribeBy { log("Interval Subscriber => $it") }
}

internal fun interval2() {
    val source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
        .map { (it + 1) * 100 }
        .take(5)

    source.subscribeBy { log("Interval2 Subscriber => $it") }
}