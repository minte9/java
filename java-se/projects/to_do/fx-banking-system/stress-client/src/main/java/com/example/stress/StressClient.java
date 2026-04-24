package com.example.stress;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StressClient {
    public static void main(String[] args) throws Exception {
        int requests = args.length > 0 ? Integer.parseInt(args[0]) : 500;
        int threads = args.length > 1 ? Integer.parseInt(args[1]) : 25;

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);
        AtomicInteger ok = new AtomicInteger();
        AtomicInteger fail = new AtomicInteger();

        for (int i = 0; i < requests; i++) {
            final int n = i;
            executor.submit(() -> {
                try {
                    start.await();
                    boolean eurToUsd = n % 2 == 0;
                    String body = eurToUsd
                            ? "{\"fromAccountId\":1,\"toAccountId\":2,\"sourceAmountMinor\":100,\"exchangeRate\":\"1.00\"}"
                            : "{\"fromAccountId\":2,\"toAccountId\":1,\"sourceAmountMinor\":100,\"exchangeRate\":\"1.00\"}";

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/transfers/fx"))
                            .header("Content-Type", "application/json")
                            .header("Idempotency-Key", UUID.randomUUID().toString())
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() >= 200 && response.statusCode() < 300) ok.incrementAndGet();
                    else fail.incrementAndGet();
                } catch (Exception e) {
                    fail.incrementAndGet();
                }
            });
        }

        long t0 = System.currentTimeMillis();
        start.countDown();
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);
        long elapsed = System.currentTimeMillis() - t0;

        System.out.println("OK: " + ok.get());
        System.out.println("FAIL: " + fail.get());
        System.out.println("Elapsed ms: " + elapsed);
        System.out.println("Requests/sec: " + (requests * 1000.0 / elapsed));
    }
}
