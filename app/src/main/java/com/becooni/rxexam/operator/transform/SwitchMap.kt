package com.becooni.rxexam.operator.transform

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun switchMap() {
    val balls = listOf("1", "3", "5")

    val source = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map(Long::toInt)
        .map { balls[it] }
        .take(balls.size.toLong())
        .doOnNext(::log)
        .switchMap { ball ->
            Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map { ball + "a" }
                .take(2)
        }

    source.subscribeBy(onNext = ::log)
}