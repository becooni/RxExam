package com.becooni.rxexam.operator

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observables.ConnectableObservable
import java.util.*

fun reactiveSum() {
    val source = userInput()

    val a: Observable<Int> = source.filter { it.startsWith("a:") }
        .map { it.replace("a:", "") }
        .map(String::toInt)

    val b: Observable<Int> = source.filter { it.startsWith("b:") }
        .map { it.replace("b:", "") }
        .map(String::toInt)

    Observable.combineLatest(
        a.startWithItem(0),
        b.startWithItem(0),
        { x, y -> x + y }
    ).subscribeBy(onNext = { log("Result: $it") })

    source.connect()
}

private fun userInput(): ConnectableObservable<String> {
    return Observable.create<String> { emitter ->
        val `in` = Scanner(System.`in`)

        while (true) {
            println("Input: ")
            val line = `in`.nextLine()
            emitter.onNext(line)

            if (line.indexOf("exit") >= 0) {
                `in`.close()
                break
            }
        }
    }.publish()
}