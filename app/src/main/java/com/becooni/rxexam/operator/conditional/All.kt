package com.becooni.rxexam.operator.conditional

import com.becooni.rxexam.BALL
import com.becooni.rxexam.getShape
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

fun all() {
    val data = listOf("1", "2", "3", "4")

    val source = Observable.fromIterable(data)
        .map(::getShape)
        .all(BALL::equals)
        .map(Boolean::toString)

    source.subscribeBy(onSuccess = ::log)
}