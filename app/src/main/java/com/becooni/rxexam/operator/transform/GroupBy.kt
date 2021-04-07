package com.becooni.rxexam.operator.transform

import com.becooni.rxexam.getShape
import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observables.GroupedObservable

fun groupBy() {
    val objects = listOf("6", "4", "2-T", "2", "6-T", "4-T")

    val source: Observable<GroupedObservable<String, String>> = Observable.fromIterable(objects)
        .groupBy(::getShape)

    source.subscribeBy(onNext = { obj ->
        obj.subscribeBy(onNext = { value ->
            log("Group: ${obj.key} \t Value: $value")
        })
    })
}