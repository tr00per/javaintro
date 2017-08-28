package sda.code.intermediate.part3.answers.concurrency;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

// Przykład zastosowania AtomicReference.
// Na podstawie opisu z https://www.infoq.com/presentations/JVM-Performance-Tuning-twitter
public class CounterWithTimestamp {

    private final long counter;
    private final Instant timestamp;

    public CounterWithTimestamp() {
        counter = 0L;
        timestamp = Instant.now();
    }

    public CounterWithTimestamp(long start) {
        counter = start;
        timestamp = Instant.now();
    }

    // bardzo ważne - inkrementacja licznika zwraca nowy obiekt
    // nasz obiekt "do zastosowań atomowych" musi być niezmienny!
    public CounterWithTimestamp increment() {
        return new CounterWithTimestamp(getCounter() + 1L);
    }

    public long getCounter() {
        return counter;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // skoro nadpisuję equals (poniżej), to muszę nadpisać też hashCode.
    // "Objects" jest standardową klasą Javy 8
    @Override
    public int hashCode() {
        return Objects.hash(counter, timestamp);
    }

    // muszę mieć możliwość porównania dwóch instancji tej klasy - nadpisuję equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterWithTimestamp that = (CounterWithTimestamp) o;
        return counter == that.counter &&
                Objects.equals(timestamp, that.timestamp);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<CounterWithTimestamp> counter =
                new AtomicReference<>(new CounterWithTimestamp());

        for (int i = 0; i < 1000; ++i) {
            new Thread(() -> {
                while (true) {
                    CounterWithTimestamp current = counter.get();

                    CounterWithTimestamp neue;
                    if (current.getCounter() > 100L) {
                        neue = current.increment();
                    } else {
                        neue = current.increment();
//                        neue = new CounterWithTimestamp(current.getCounter() / 2L);
                    }

                    if (counter.compareAndSet(current, neue)) {
                        System.out.println("OK");
                        break; // <- tu
                    } else {
                        System.err.println("Fail");
                    }
                }
            }).start();
        }
        Thread.sleep(1000);

        System.out.println(counter.get().getCounter());
        System.out.println(counter.get().getTimestamp());
    }
}
