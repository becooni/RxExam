package com.becooni.rxexam.operator.filter

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun filter() {
    val objs = listOf(
        "1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"
    )

    val source = Observable.fromIterable(objs)
        .filter { it.endsWith("CIRCLE") }

    source.subscribeBy { log("Filter Subscriber => $it") }
}

internal fun filter2() {
    val data = listOf(100, 34, 27, 99, 50)

    val source = Observable.fromIterable(data)
        .filter { it % 2 == 0 }

    source.subscribeBy { log("Filter2 Subscriber => $it") }
}

internal fun filter3() {
    val numbers = listOf(100, 200, 300, 400, 500)

    var single: Single<Int> = Observable.fromIterable(numbers).first(-1)
    single.subscribeBy { log("Filter3 Subscriber first(-1) => $it") }

    single = Observable.fromIterable(numbers).last(999)
    single.subscribeBy { log("Filter3 Subscriber last(999) => $it") }

    var source: Observable<Int> = Observable.fromIterable(numbers).take(3)
    source.subscribeBy { log("Filter3 Subscriber take(3) => $it") }

    source = Observable.fromIterable(numbers).takeLast(3)
    source.subscribeBy { log("Filter3 Subscriber takeLast(3) => $it") }

    source = Observable.fromIterable(numbers).skip(2)
    source.subscribeBy { log("Filter3 Subscriber skip(2) => $it") }

    source = Observable.fromIterable(numbers).skipLast(2)
    source.subscribeBy { log("Filter3 Subscriber skipLast(2) => $it") }
}