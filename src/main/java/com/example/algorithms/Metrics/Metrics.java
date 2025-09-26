package algorithms.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    private final AtomicLong comparisons = new AtomicLong();
    private final AtomicLong allocations = new AtomicLong();
    private final AtomicLong recursionDepth = new AtomicLong();
    private final AtomicLong maxDepth = new AtomicLong();

    public void incComparisons() { comparisons.incrementAndGet(); }
    public void incAllocations() { allocations.incrementAndGet(); }
    public void incAllocations(long n) { allocations.addAndGet(n); }

    public void enterRecursion() {
        long d = recursionDepth.incrementAndGet();
        maxDepth.accumulateAndGet(d, Math::max);
    }

    public void leaveRecursion() { recursionDepth.decrementAndGet(); }

    public long getComparisons() { return comparisons.get(); }
    public long getAllocations() { return allocations.get(); }
    public long getMaxDepth() { return maxDepth.get(); }

    public void reset() {
        comparisons.set(0);
        allocations.set(0);
        recursionDepth.set(0);
        maxDepth.set(0);
    }
}
