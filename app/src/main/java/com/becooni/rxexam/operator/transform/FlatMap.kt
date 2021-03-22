package com.becooni.rxexam.operator.transform

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun flatMap() {
    val getDoubleDiamonds = { ball: String ->
        Observable.just("$ball♢", "$ball♢")
    }

    val balls = listOf("1", "3", "5")

    val source = Observable.fromIterable(balls)
        .flatMap(getDoubleDiamonds)
    source.subscribeBy { log("FlatMap Subscriber => $it") }
}