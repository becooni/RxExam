package com.becooni.rxexam.source

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun observable() {
    Observable.just("Hello World")
        .subscribeBy { log("Observable Subscriber => $it") }
}