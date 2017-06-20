package com.netikras.studies.studentbuddy.commons.tools;

/**
 * Created by netikras on 16.12.28.
 */
public class TimeBenchmark {

    private double[] takes;
    private int takeId = 0;

    private int defaultTakesCount = 100;

    private double totalDuration_ns = 0;
    private double avgDuration_ns = 0;

    public void estimateTakesCount(int count) {
        this.takes = new double[count+2];
    }

    public void take() {
        takes[takeId++] = System.nanoTime();
    }


    public void start() {
        takeId = 0;
        if (takes == null) {
            takes = new double[defaultTakesCount];
        }
        take();
    }

    public void stop() {
        take();

        totalDuration_ns = takes[takeId-1] - takes[0];
        avgDuration_ns = totalDuration_ns/takeId;

    }


    public double[] getTakes() {
        return takes;
    }

    public int getTakeId() {
        return takeId;
    }

    public int getDefaultTakesCount() {
        return defaultTakesCount;
    }

    public double getTotalDuration_ns() {
        return totalDuration_ns;
    }

    public double getAvgDuration_ns() {
        return avgDuration_ns;
    }

    public String getResultsSummaryString() {
        StringBuilder builder = new StringBuilder("Time Benchmarks Results: ");

        builder.append("Total takes: ").append(takeId-2).append("; ");
        builder.append(String.format("Total duration: %.0f ns (= %.4f ms; %.4f s ) ", totalDuration_ns, totalDuration_ns/1000/1000, totalDuration_ns/1000/1000/1000));
        builder.append(String.format("Average duration: %.0f ns (= %.4f ms; %.4f s ) ", avgDuration_ns, avgDuration_ns/1000/1000, avgDuration_ns/1000/1000/1000));

        return builder.toString();
    }

    public String getAllResultsString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Benchmark started @%.0f \n", takes[0]));

        for (int i = 1; i < takeId-1; i++) {
            double timestamp = takes[i];
            double previous = takes[i-1];

            double diff = timestamp-previous;

            builder.append(String.format("Take %d: timestamp_start: %.0f, timestamp_finish: %.0f, duration: %.0f ns (= %.4f ms; %.4f s ) ", i, previous, timestamp, diff, diff/1000/1000, diff/1000/1000/1000)).append("\n");
        }

        return builder.toString();
    }




}
