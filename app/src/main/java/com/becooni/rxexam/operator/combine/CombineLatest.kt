package com.becooni.rxexam.operator.combine

import com.becooni.rxexam.getColor
import com.becooni.rxexam.getSuffix
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun combineLatest() {
    val data1 = listOf("6", "7", "4", "2")
    val data2 = listOf("DIAMOND", "STAR", "PENTAGON")

    val source = Observable.combineLatest(
        Observable.fromIterable(data1)
            .zipWith(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                { shape, _ -> getColor(shape) }
            ),
        Observable.fromIterable(data2)
            .zipWith(
                Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                { shape, _ -> getSuffix(shape) }
            ),
        { v1, v2 -> v1 + v2 }
    )

    source.subscribeBy(onNext = ::log)
}