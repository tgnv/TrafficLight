package io.khasang.javaweb20170319;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficLight {

    private TrafficLight() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
    }

    public static void main(String[] args) {
        new TrafficLight();
    }
}

