package com.becooni.rxexam.operator

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun multiplicationTable() {
    val source = Observable.range(2, 8)
        .flatMap { dan ->
            Observable.range(1, 9)
                .map { "${dan}x$it=${dan * it}" }
        }
    source.subscribeBy { log("MultiplicationTable Subscriber => $it") }
}

internal fun multiplicationTable2() {
    val source = Observable.range(2, 8)
        .flatMap(
            {
                Observable.range(1, 9)
            },
            { t1, t2 ->
                "${t1}x$t2=${t1 * t2}"
            }
        )
    source.subscribeBy { log("MultiplicationTable2 Subscriber => $it") }
}