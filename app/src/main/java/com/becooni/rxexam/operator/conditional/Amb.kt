package com.becooni.rxexam.operator.conditional

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun amb() {
    val data1 = listOf("1", "3", "5")
    val data2 = listOf("2-R", "4-R")

    val sources = listOf(
        Observable.fromIterable(data1)
            .doOnComplete { log("Observable #1 : onComplete()") },
        Observable.fromIterable(data2)
            .delay(100L, TimeUnit.MILLISECONDS)
            .doOnComplete { log("Observable #1 : onComplete()") }
    )

    Observable.amb(sources)
        .doOnComplete { log("Result : onComplete()") }
        .subscribeBy(onNext = ::log)
}