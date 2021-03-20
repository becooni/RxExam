package com.becooni.rxexam

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.becooni.rxexam.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observable()
        asyncSubject()
        behaviorSubject()
        publishSubject()
        connectableObservable()
        map()
        flatMap()
        multiplicationTable()
        multiplicationTable2()
        filter()
        filter2()
        filter3()
        reduce()
        mapFilterReduce()

        Test().map()
    }

    private fun observable() {
        Observable.just("Hello World").subscribeBy(
            onNext = { binding.textView.text = it },
            onComplete = { toast("onComplete !!") },
            onError = Throwable::printStackTrace
        )
    }

    private fun asyncSubject() {
        val subject = AsyncSubject.create<String>()
        subject.subscribeBy { log("AsyncSubject Subscriber #1 => $it") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribeBy { log("AsyncSubject Subscriber #2 => $it") }
        subject.onNext("5")
        subject.onComplete()
    }

    private fun behaviorSubject() {
        val subject = BehaviorSubject.createDefault("6")
        subject.subscribeBy { log("BehaviorSubject Subscriber #1 => $it") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribeBy { log("BehaviorSubject Subscriber #2 => $it") }
        subject.onNext("5")
        subject.onComplete()
    }

    private fun publishSubject() {
        val subject = PublishSubject.create<String>()
        subject.subscribeBy { log("PublishSubject Subscriber #1 => $it") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribeBy { log("PublishSubject Subscriber #2 => $it") }
        subject.onNext("5")
        subject.onComplete()
    }

    private fun connectableObservable() {
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

    private fun map() {

        val getDiamond: Function<String, String> = Function { "$it♢" }

        val getDiamond2: (String) -> String = { "$it♢" }

        val balls = listOf("1", "2", "3", "5")

        val source = Observable.fromIterable(balls)
            .map(getDiamond2)
        source.subscribeBy { log("Map Subscriber => $it") }
    }

    private fun flatMap() {
        val getDoubleDiamonds = { ball: String ->
            Observable.just("$ball♢", "$ball♢")
        }

        val balls = listOf("1", "3", "5")

        val source = Observable.fromIterable(balls)
            .flatMap(getDoubleDiamonds)
        source.subscribeBy { log("FlatMap Subscriber => $it") }
    }

    private fun multiplicationTable() {
        val source = Observable.range(2, 8)
            .flatMap { dan ->
                Observable.range(1, 9)
                    .map { "${dan}x$it=${dan * it}" }
            }
        source.subscribeBy { log("MultiplicationTable Subscriber => $it") }
    }

    private fun multiplicationTable2() {
        val source = Observable.range(2, 8)
            .flatMap(
                {
                    Observable.range(1, 9)
                },
                { t1, t2 ->
                    "${t1}x$t2=${t1 * t2}"
                }
            )
        source.subscribeBy { log("MultiplicationTable2 Subscriber => $it") }
    }

    private fun filter() {
        val objs = listOf(
            "1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"
        )

        val source = Observable.fromIterable(objs)
            .filter { it.endsWith("CIRCLE") }

        source.subscribeBy { log("Filter Subscriber => $it") }
    }

    private fun filter2() {
        val data = listOf(100, 34, 27, 99, 50)

        val source = Observable.fromIterable(data)
            .filter { it % 2 == 0 }

        source.subscribeBy { log("Filter2 Subscriber => $it") }
    }

    private fun filter3() {
        val numbers = listOf(100, 200, 300, 400, 500)

        var single: Single<Int> = Observable.fromIterable(numbers).first(-1)
        single.subscribeBy { log("Filter3 Subscriber first(-1) => $it") }

        single = Observable.fromIterable(numbers).last(999)
        single.subscribeBy { log("Filter3 Subscriber last(999) => $it") }

        var source: Observable<Int> = Observable.fromIterable(numbers).take(3)
        source.subscribeBy { log("Filter3 Subscriber take(3) => $it") }

        source = Observable.fromIterable(numbers).takeLast(3)
        source.subscribeBy { log("Filter3 Subscriber takeLast(3) => $it") }

        source = Observable.fromIterable(numbers).skip(2)
        source.subscribeBy { log("Filter3 Subscriber skip(2) => $it") }

        source = Observable.fromIterable(numbers).skipLast(2)
        source.subscribeBy { log("Filter3 Subscriber skipLast(2) => $it") }
    }

    private fun reduce() {
        val balls = listOf("1", "3", "5")

        val source = Observable.fromIterable(balls)
            .reduce { ball1, ball2 -> "$ball2(${ball1})" }
        source.subscribeBy { log("Reduce Subscriber => $it") }
    }

    private fun mapFilterReduce() {
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
}

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun log(text: String) {
    Log.e("RXTEST", text)
}