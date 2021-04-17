package com.becooni.rxexam.operator.utility

import com.becooni.rxexam.doSomething
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun timeInterval() {
    val data = listOf("1", "3", "7")

    val source = Observable.fromIterable(data)
        .delay {
            doSomething()
            Observable.just(it)
        }
        .timeInterval()

    source.subscribeBy(onNext = { log(it.toString()) })
}