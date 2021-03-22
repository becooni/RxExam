package com.becooni.rxexam.operator.create

import com.becooni.rxexam.OkHttpHelper
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun repeat() {
    val balls = listOf("1", "3", "5")

    val source = Observable.fromIterable(balls)
        .repeat(3)

    source.subscribeBy { log("Repeat Subscriber => $it") }
    source.subscribeBy(onComplete = { log("Repeat Subscriber onComplete") })
}

fun heartbeat() {
    val url = "https://api.github.com/zen"

    Observable.timer(2, TimeUnit.SECONDS)
        .map { url }
        .map(OkHttpHelper::fetch)
        .repeat()
        .subscribe(
            {
                log("HeartBeat Subscriber => $it")
            },
            Throwable::printStackTrace,
            {
                log("HeartBeat Subscriber => Complete")
            }
        )
}