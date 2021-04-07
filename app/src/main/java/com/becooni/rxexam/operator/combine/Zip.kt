package com.becooni.rxexam.operator.combine

import com.becooni.rxexam.BALL
import com.becooni.rxexam.BLUE
import com.becooni.rxexam.GREEN
import com.becooni.rxexam.PENTAGON
import com.becooni.rxexam.RED
import com.becooni.rxexam.STAR
import com.becooni.rxexam.getColor
import com.becooni.rxexam.getSuffix
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun zip() {
    val shapes = listOf(BALL, PENTAGON, STAR)
    val coloredTriangles = listOf("2-T", "6-T", "4-T")

    val source = Observable.zip(
        Observable.fromIterable(shapes).map(::getSuffix),
        Observable.fromIterable(coloredTriangles).map(::getColor),
        { suffix, color -> color + suffix }
    )

    source.subscribeBy(onNext = ::log)
}

fun zipNumbers() {
    val source = Observable.zip(
        Observable.just(100, 200, 300),
        Observable.just(10, 20, 30),
        Observable.just(1, 2, 3),
        { a, b, c -> (a + b + c).toString() }
    )

    source.subscribeBy(onNext = ::log)
}

fun zipExample() {
    val source = Observable.zip(
        Observable.just(RED, GREEN, BLUE),
        Observable.interval(200L, TimeUnit.MILLISECONDS),
        { value, _ -> value }
    )

    source.subscribeBy(onNext = ::log)
}