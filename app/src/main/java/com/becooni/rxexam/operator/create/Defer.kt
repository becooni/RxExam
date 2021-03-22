package com.becooni.rxexam.operator.create

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

object Defer {

    private val colors = listOf("1", "3", "5", "6").iterator()

    private fun getObservable(): Observable<String> {
        if (colors.hasNext()) {
            val color = colors.next()
            return Observable.just(color, "$color-R", "$color-P")
        }
        return Observable.empty()
    }

    fun defer() {
        val supplier = { getObservable() }
        val source = Observable.defer(supplier)

        source.subscribeBy { log("Defer Subscriber #1 => $it") }
        source.subscribeBy { log("Defer Subscriber #2 => $it") }
    }

    fun notDeferred() {
        val source = getObservable()

        source.subscribeBy { log("NotDeferred Subscriber #1 => $it") }
        source.subscribeBy { log("NotDeferred Subscriber #2 => $it") }
    }
}