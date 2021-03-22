package com.becooni.rxexam.operator.create

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun range() {
    val source = Observable.range(1, 10)
        .filter { it % 2 == 0 }

    source.subscribeBy { log("Range Subscriber => $it") }
}