package design.patterns;

import io.reactivex.rxjava3.core.Observable;

public class ReactiveAsync {

    public static void main(String[] args) {
        Observable<String> observable = Observable.just("Promise1", "Promise2", "Promise3");

        observable
                .map(String::toUpperCase)
                .subscribe(s -> System.out.println(s));

    }
}
