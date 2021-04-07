package com.becooni.rxexam.operator.transform

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun scan() {
    val balls = listOf("1", "3", "5")

    val source = Observable.fromIterable(balls)
        .scan { ball1, ball2 ->
            "$ball2 ($ball1)"
        }
    source.subscribeBy(onNext = ::log)
}