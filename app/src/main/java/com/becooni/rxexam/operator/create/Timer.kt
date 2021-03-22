package com.becooni.rxexam.operator.create

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

internal fun timer() {
    val source = Observable.timer(500L, TimeUnit.MILLISECONDS)
        .map { SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ROOT).format(Date()) }

    source.subscribeBy { log("Timer Subscriber => $it") }
}