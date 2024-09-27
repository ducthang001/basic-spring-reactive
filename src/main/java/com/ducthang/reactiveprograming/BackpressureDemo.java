package com.ducthang.reactiveprograming;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackpressureDemo {

    private Flux<Long> createNoOverFlow() {
        return Flux.range(1, Integer.MAX_VALUE)
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100))); // simulate that processing takes time
    }

    private Flux<Long> createOverFlow() {
        return Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100))); // simulate that processing takes time
    }

    private Flux<Long> createDropOnBackPressure() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop()
                .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a))
                .doOnNext(a -> System.out.println("Element kept by consumer: " + a));

    }

    private Flux<Long> createBufferOnBackPressure() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50, BufferOverflowStrategy.DROP_LATEST) // luu cac request qua nhieu ve buffer
                .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a))
                .doOnNext(a -> System.out.println("Element kept by consumer: " + a));

    }

    public static void main(String[] args) {
        BackpressureDemo demo = new BackpressureDemo();
        demo.createBufferOnBackPressure()
                .blockLast();
    }

    // Backpressure: giam tai cho he thong khi co qua nhieu yeu cau dong thoi
}
