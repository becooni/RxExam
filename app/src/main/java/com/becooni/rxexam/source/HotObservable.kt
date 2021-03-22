package com.becooni.rxexam.source

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

internal fun asyncSubject() {
    val subject = AsyncSubject.create<String>()
    subject.subscribeBy { log("AsyncSubject Subscriber #1 => $it") }
    subject.onNext("1")
    subject.onNext("3")
    subject.subscribeBy { log("AsyncSubject Subscriber #2 => $it") }
    subject.onNext("5")
    subject.onComplete()
}

internal fun behaviorSubject() {
    val subject = BehaviorSubject.createDefault("6")
    subject.subscribeBy { log("BehaviorSubject Subscriber #1 => $it") }
    subject.onNext("1")
    subject.onNext("3")
    subject.subscribeBy { log("BehaviorSubject Subscriber #2 => $it") }
    subject.onNext("5")
    subject.onComplete()
}

internal fun publishSubject() {
    val subject = PublishSubject.create<String>()
    subject.subscribeBy { log("PublishSubject Subscriber #1 => $it") }
    subject.onNext("1")
    subject.onNext("3")
    subject.subscribeBy { log("PublishSubject Subscriber #2 => $it") }
    subject.onNext("5")
    subject.onComplete()
}

internal fun connectableObservable() {
    val dt = arrayOf("1", "3", "5")

    val balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
        .map(Long::toInt)
        .map { dt[it] }
        .take(dt.size.toLong())

    val source = balls.publish()

    source.subscribeBy { log("ConnectableObservable Subscriber #1 => $it") }
    source.subscribeBy { log("ConnectableObservable Subscriber #2 => $it") }
    source.connect()

    Thread.sleep(250)
    source.subscribeBy { log("ConnectableObservable Subscriber #3 => $it") }
    Thread.sleep(100)
}