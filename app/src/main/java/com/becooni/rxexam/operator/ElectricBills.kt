package com.becooni.rxexam.operator

import com.becooni.rxexam.log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.text.DecimalFormat
import kotlin.math.max
import kotlin.math.min

private val data = listOf(
    "100", // 910 + 93.3 * 100 = 10,240원
    "300" // 1600 + 93.3 * 200 + 187.9 * 100 = 39,050원
)

private var index = 0 // 사용해선 안됨

fun electricBillV1() {
    val basePrice = Observable.fromIterable(data)
        .map(String::toInt)
        .map { value ->
            when (value) {
                in 0..200 -> 910
                in 201..400 -> 1600
                else -> 7300
            }
        }

    val usagePrice = Observable.fromIterable(data)
        .map(String::toInt)
        .map { value ->
            val series1 = min(200, value) * 93.3
            val series2 = min(200, max(value - 200, 0)) * 187.9
            val series3 = min(0, max(value - 400, 0)) * 280.65
            (series1 + series2 + series3).toInt()
        }

    val source = Observable.zip(
        basePrice,
        usagePrice,
        { v1, v2 -> v1 + v2 }
    )

    source.map { value -> DecimalFormat("#,###").format(value) }
        .subscribeBy(onNext = { value ->
            val sb = StringBuilder().append("Usage: ${data[index]} kWh => ")
                .append("Price: ${value}원")

            log(sb.toString())

            index++
        })
}

fun electricBillV2() {
    val basePrice = Observable.fromIterable(data)
        .map(String::toInt)
        .map { value ->
            when (value) {
                in 0..200 -> 910
                in 201..400 -> 1600
                else -> 7300
            }
        }

    val usagePrice = Observable.fromIterable(data)
        .map(String::toInt)
        .map { value ->
            val series1 = min(200, value) * 93.3
            val series2 = min(200, max(value - 200, 0)) * 187.9
            val series3 = min(0, max(value - 400, 0)) * 280.65
            (series1 + series2 + series3).toInt()
        }

    val source: Observable<Pair<String, Int>> = Observable.zip(
        basePrice,
        usagePrice,
        Observable.fromIterable(data),
        { v1, v2, i -> i to v1 + v2 }
    )

    source.map { value -> value.first to DecimalFormat("#,###").format(value.second) }
        .subscribeBy(onNext = { value ->
            val sb = StringBuilder().append("Usage: ${value.first} kWh => ")
                .append("Price: ${value.second}원")

            log(sb.toString())
        })
}