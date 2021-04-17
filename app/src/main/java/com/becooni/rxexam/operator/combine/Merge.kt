package com.becooni.rxexam.operator.combine

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun merge() {
    val data1 = listOf("1", "3")
    val data2 = listOf("2", "4", "6")

    val source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
        .map(Long::toInt)
        .map { data1[it] }
        .take(data1.size.toLong())

    val source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
        .map(Long::toInt)
        .map { data2[it] }
        .take(data2.size.toLong())

    val source = Observable.merge(source1, source2)

    source.subscribeBy(onNext = ::log)
}