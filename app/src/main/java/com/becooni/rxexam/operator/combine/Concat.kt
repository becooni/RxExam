package com.becooni.rxexam.operator.combine

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun concat() {
    val completeAction = { log("onComplete()") }

    val data1 = listOf("1", "3", "5")
    val data2 = listOf("2", "4", "6")

    val source1 = Observable.fromIterable(data1)
        .doOnComplete(completeAction)

    val source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map(Long::toInt)
        .map { data2[it] }
        .take(data2.size.toLong())
        .doOnComplete(completeAction)

    val source = Observable.concat(source1, source2)
        .doOnComplete(completeAction)

    source.subscribeBy(onNext = ::log)
}