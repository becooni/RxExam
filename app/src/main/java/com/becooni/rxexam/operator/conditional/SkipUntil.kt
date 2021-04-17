package com.becooni.rxexam.operator.conditional

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun skipUntil() {
    val data = listOf("1", "2", "3", "4", "5", "6")

    val source = Observable.fromIterable(data)
        .zipWith(
            Observable.interval(100L, TimeUnit.MILLISECONDS),
            { value, _ -> value }
        )
        .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS))

    source.subscribeBy(onNext = ::log)
}