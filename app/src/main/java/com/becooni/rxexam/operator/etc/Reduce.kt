package com.becooni.rxexam.operator.etc

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun reduce() {
    val balls = listOf("1", "3", "5")

    val source = Observable.fromIterable(balls)
        .reduce { ball1, ball2 -> "$ball2(${ball1})" }
    source.subscribeBy { log("Reduce Subscriber => $it") }
}