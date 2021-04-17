package com.becooni.rxexam.operator.utility

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun delay() {
    val data = listOf("1", "7", "2", "3", "4")

    val source = Observable.fromIterable(data)
        .delay(100L, TimeUnit.MILLISECONDS)

    source.subscribeBy(onNext = ::log)
}