package com.ducthang.reactiveprograming;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReactiveDemo {

    private Mono<String> testMono() {
        return Mono.empty();
    }

    private Flux<String> testFlux() {
//        return Flux.just("Java", "Cpp", "Dart");
        List<String> listPg = List.of("Java", "Cpp", "Dart");
        return Flux.fromIterable(listPg); // convert iterable from list
    }

    private Flux<String> testMap() {
        Flux<String> flux = Flux.just("Java", "Cpp", "Dart");
        return flux.map(s -> s.toUpperCase(Locale.ROOT));
    }

    private Flux<String> testFlatMap() {
        Flux<String> flux = Flux.just("Java", "Cpp", "Dart");
        return flux.flatMap(s -> Mono.just(s.toUpperCase(Locale.ROOT)));
        // only item for iterable
    }

    private Flux<String> testSkip() {
        // Basic skip
//        Flux<String> flux = Flux.just("Java", "Cpp", "Dart");
//        return flux.delayElements(Duration.ofSeconds(1))
//                .log();

        Flux<String> flux = Flux.just("Java", "Cpp", "Dart").delayElements(Duration.ofSeconds(1));

//        return flux.skip(Duration.ofSeconds(2)); // skip 1 element
//        return flux.skip(Duration.ofMillis(2010)); // skip 2 elements
        return flux.skipLast(2);
    }

    public Flux<Integer> testComplexSkip() {
        // Complex Skip
        Flux<Integer> flux = Flux.range(1, 20);
        //return flux.skipWhile(integer -> integer > 10); // condition skip
        return flux.skipUntil(integer -> integer == 10);
    }

    private Flux<Integer> testConcat() {
        Flux<Integer> flux1 = Flux.range(1, 20);
        Flux<Integer> flux2 = Flux.range(101, 20);
        Flux<Integer> flux3 = Flux.range(1001, 20);
        return Flux.concat(flux1, flux2, flux3);
    }

    // concat != merge. Concat is concat (complete 1 and start 2), merge 1 and 2 immediately
    private Flux<Integer> testMerge() {
        Flux<Integer> flux1 = Flux.range(1, 20)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20)
                .delayElements(Duration.ofMillis(500));
        return Flux.merge(flux1, flux2);
    }

    // gia su flux 1 chi co 10 phan tu thi result se chi co 10 phan tu ma ko co 20
    private Flux<Tuple2<Integer, Integer>> testZip() {
        Flux<Integer> flux1 = Flux.range(1, 20)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20)
                .delayElements(Duration.ofMillis(500));
        return Flux.zip(flux1, flux2);
    }

    // Uu tien so luong phan tu nho nhat truoc
    private Flux<Tuple3<Integer, Integer, Integer>> testZipComplex() {
        Flux<Integer> flux1 = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.range(101, 20)
                .delayElements(Duration.ofMillis(500));
        Mono<Integer> mono = Mono.just(1);
        return Flux.zip(flux1, flux2, mono);
    }

    private Mono<List<Integer>> testCollect() {
        Flux<Integer> flux = Flux.range(1, 20)
                .delayElements(Duration.ofMillis(500));
        return flux.collectList();
    }

    private Flux<List<Integer>> testBuffer() {
        Flux<Integer> flux = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(1000));
        return flux.buffer(Duration.ofMillis(3100)); // delay buffer
    }

    private Mono<Map<Integer, Integer>> testMapCollection() {


        Flux<Integer> flux = Flux.range(1, 10);
        return flux.collectMap(integer -> integer, integer -> integer * integer);
    }

    private Flux<Integer> testDoFunctions() {
        Flux<Integer> flux = Flux.range(1, 10);
        return flux.doOnEach(signal -> {
            if(signal.getType() == SignalType.ON_COMPLETE) {
                System.out.println("Done");
            } else {
                System.out.println(signal.get());
            }
        });
    }

    private Flux<Integer> testDoFunctions2() {
        Flux<Integer> flux = Flux.range(1, 10);
        return flux.doOnComplete(() -> System.out.println("Done"));
    }

    private Flux<Integer> testDoFunctions3() {
        Flux<Integer> flux = Flux.range(1, 10);
        return flux.doOnNext(integer -> System.out.println(integer));
    }

    private Flux<Integer> testDoFunctions4() {
        Flux<Integer> flux = Flux.range(1, 10);
        return flux.doOnSubscribe(subscription -> System.out.println("subscription")); // dang ki va duoc chay them lan nua
    }

    private Flux<Integer> testDoFunctions5() {
        Flux<Integer> flux = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500));
        return flux.doOnCancel(() -> System.out.println("cancel"));
    }

    private Flux<Integer> testErrorHandle() {
        Flux<Integer> flux = Flux.range(1, 10)
                .map(integer -> {
                    if(integer == 5) {
                        throw new RuntimeException("Unexpected Error");
                    }
                    return integer;
                });
        return flux.onErrorContinue((throwable, o) -> System.out.println("Don't worry about error " + o));
    }

    private Flux<Integer> testErrorHandle2() {
        Flux<Integer> flux = Flux.range(1, 10)
                .map(integer -> {
                    if(integer == 5) {
                        throw new RuntimeException("Unexpected Error");
                    }
                    return integer;
                });
//        return flux
//                .onErrorReturn(RuntimeException.class, -1);
        return flux.onErrorResume(throwable -> Flux.range(100, 5)); // or Mono.just(5)
    }

    private Flux<Integer> testErrorHandle3() {
        Flux<Integer> flux = Flux.range(1, 10)
                .map(integer -> {
                    if(integer == 5) {
                        throw new RuntimeException("Unexpected Error");
                    }
                    return integer;
                });

        return flux.onErrorMap(throwable -> new UnsupportedOperationException(throwable.getMessage()));
    }

    public static void main(String[] args) throws InterruptedException {
        ReactiveDemo demo = new ReactiveDemo();
//        demo.testMono()
//            .subscribe(System.out::println);

//        demo.testDoFunctions()
//                .subscribe();

//        demo.testDoFunctions3()
//                .subscribe(System.out::println);

//        //=== doOnCancel()
//        Disposable disposable = demo.testDoFunctions5()
//                .subscribe(System.out::println);
//        Thread.sleep(3500); // thread da dung va cancel duoc thuc thi
//        disposable.dispose();
//        //===

        // === doOnError()
        Disposable disposable = demo.testErrorHandle3()
                .subscribe(System.out::println);
        //

        // khi su dung block se lam cho toan bo tro thanh non-reactive
        // neu su dung delay thi se cho cho den khi toan bo thuc thi het moi in ra
//        List<Integer> output = demo.testCollect().block();
//        System.out.println(output);

        // Set thread, because flux.delayElement spend 1 second to print all elements
        // and all element not print for 1 second (3 elements spend 3 seconds)
//        Thread.sleep(12000); // 10 seconds
    }
}
