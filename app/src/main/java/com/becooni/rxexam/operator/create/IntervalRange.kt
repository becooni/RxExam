package com.becooni.rxexam.operator.create

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun intervalRange() {
    val source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS)

    source.subscribeBy { log("IntervalRange Subscriber => $it") }
}

fun intervalRangeMakeWithInterval() {
    val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map { it + 1 }
        .take(5)

    source.subscribeBy { log("IntervalRangeMakeWithInterval Subscriber => $it") }

}