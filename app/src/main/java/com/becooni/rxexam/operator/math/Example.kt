package com.becooni.rxexam.operator.math

import com.becooni.rxexam.log
import hu.akarnokd.rxjava3.math.MathFlowable
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun mathExample() {
    val data = listOf(1, 2, 3, 4)

    // 1. count
    Observable.fromIterable(data)
        .count()
        .map { "count: $it" }
        .subscribeBy(onSuccess = ::log)

    // 2. max & min
    Flowable.fromIterable(data)
        .to(MathFlowable::max)
        .map { "max: $it" }
        .subscribeBy(onNext = ::log)

    Flowable.fromIterable(data)
        .to(MathFlowable::min)
        .map { "min: $it" }
        .subscribeBy(onNext = ::log)

    // 3. sum & average
    Flowable.fromIterable(data)
        .to(MathFlowable::sumInt)
        .map { "sum: $it" }
        .subscribeBy(onNext = ::log)

    Observable.fromIterable(data)
        .toFlowable(BackpressureStrategy.BUFFER)
        .to(MathFlowable::averageDouble)
        .map { "average: $it" }
        .subscribeBy(onNext = ::log)
}