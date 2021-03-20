package com.becooni.rxexam;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

public class Test {

    Function<String, String> getDiamond = ball -> ball + "♢";

    public void map() {
        String[] balls = {"1", "2", "3", "5"};

        Observable<String> source = Observable.fromArray(balls).map(getDiamond);
        source.subscribe(result -> System.out.println("MAPMAP " + result));
    }
}
