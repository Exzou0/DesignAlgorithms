
package com.example.algorithms.bench;

import algorithms.MergeSort;
import algorithms.Quicksort;
import algorithms.DeterministicSelect;
import algorithms.ClosestPair;
import algorithms.metrics.Metrics;
import com.example.algorithms.util.CsvWriter;

import java.io.IOException;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws IOException {
        CsvWriter writer = new CsvWriter(
                "results/benchmarks.csv",
                "algo", "n", "timeNs", "comparisons", "allocations", "depth"
        );

        int[] sizes = {100, 1000, 5000, 10000, 20000};

        for (int n : sizes) {
            int[] arr = new Random().ints(n, 0, n * 10).toArray();

            // MergeSort
            Metrics m = new Metrics();
            int[] copy1 = arr.clone();
            long t1 = System.nanoTime();
            MergeSort.sort(copy1, m);
            long t2 = System.nanoTime();
            writeRow(writer, "mergesort", n, t2 - t1, m);

            // QuickSort
            m.reset();
            int[] copy2 = arr.clone();
            long q1 = System.nanoTime();
            Quicksort.sort(copy2, m);
            long q2 = System.nanoTime();
            writeRow(writer, "quicksort", n, q2 - q1, m);

            // Select
            m.reset();
            int[] copy3 = arr.clone();
            long s1 = System.nanoTime();
            DeterministicSelect.select(copy3, n / 2, m);
            long s2 = System.nanoTime();
            writeRow(writer, "select", n, s2 - s1, m);

            // ClosestPair
            m.reset();
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            Random r = new Random();
            for (int i = 0; i < n; i++) pts[i] = new ClosestPair.Point(r.nextDouble(), r.nextDouble());
            long c1 = System.nanoTime();
            ClosestPair.findClosest(pts, m);
            long c2 = System.nanoTime();
            writeRow(writer, "closest", n, c2 - c1, m);
        }
    }

    private static void writeRow(CsvWriter writer, String algo, int n, long timeNs, Metrics m) throws IOException {
        writer.writeRow(
                algo,
                String.valueOf(n),
                String.valueOf(timeNs),
                String.valueOf(m.getComparisons()),
                String.valueOf(m.getAllocations()),
                String.valueOf(m.getMaxDepth())
        );
    }




}

