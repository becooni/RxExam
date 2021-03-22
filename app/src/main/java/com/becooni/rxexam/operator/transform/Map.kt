package com.becooni.rxexam.operator.transform

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun map() {

    val getDiamond: (String) -> String = { "$it♢" }

    val balls = listOf("1", "2", "3", "5")

    val source = Observable.fromIterable(balls)
        .map(getDiamond)
    source.subscribeBy { log("Map Subscriber => $it") }
}