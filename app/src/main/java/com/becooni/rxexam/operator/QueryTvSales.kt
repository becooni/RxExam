package com.becooni.rxexam.operator

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

internal fun mapFilterReduce() {
    val sales = mutableListOf<Pair<String, Int>>()

    sales.add("TV" to 2500)
    sales.add("Camera" to 300)
    sales.add("TV" to 1600)
    sales.add("Phone" to 800)

    val source = Observable.fromIterable(sales)
        .filter { it.first == "TV" }
        .map { it.second }
        .reduce { t1, t2 -> t1 + t2 }

    source.subscribeBy { log("MapFilterReduce Subscriber => $it") }
}