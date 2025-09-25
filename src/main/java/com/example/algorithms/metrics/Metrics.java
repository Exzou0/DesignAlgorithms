package com.example.algorithms.metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class Metrics {
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong allocations = new AtomicLong(0);
    private final AtomicInteger maxDepth = new AtomicInteger(0);

    private final ThreadLocal<Integer> currentDepth = ThreadLocal.withInitial(() -> 0);

    public void incComparisons() {
        comparisons.incrementAndGet();
    }

    public void incComparisons(long n) {
        comparisons.addAndGet(n);
    }

    public long getComparisons() {
        return comparisons.get();
    }

    public void incAllocations() {
        allocations.incrementAndGet();
    }

    public void incAllocations(long n) {
        allocations.addAndGet(n);
    }

    public long getAllocations() {
        return allocations.get();
    }

    public int getMaxDepth() {
        return maxDepth.get();
    }


    public void enter() {
        int d = currentDepth.get() + 1;
        currentDepth.set(d);
        maxDepth.updateAndGet(prev -> Math.max(prev, d));
    }


    public void exit() {
        int d = currentDepth.get() - 1;
        currentDepth.set(Math.max(d, 0));
    }

    public void reset() {
        comparisons.set(0);
        allocations.set(0);
        maxDepth.set(0);
        currentDepth.set(0);
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + getComparisons() +
                ", allocations=" + getAllocations() +
                ", maxDepth=" + getMaxDepth() +
                '}';
    }
}
