package com.netikras.studies.studentbuddy.commons.tools;

/**
 * Created by netikras on 16.12.28.
 */
public class TimeBenchmark {

    private double[] takes;
    private int takeId = 0;

    private int defaultTakesCount = 100;

    private double totalDurationNs = 0;
    private double avgDurationNs = 0;

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

        totalDurationNs = takes[takeId-1] - takes[0];
        avgDurationNs = totalDurationNs /takeId;

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

    public double getTotalDurationNs() {
        return totalDurationNs;
    }

    public double getAvgDurationNs() {
        return avgDurationNs;
    }

    public String getResultsSummaryString() {
        StringBuilder builder = new StringBuilder("Time Benchmarks Results: ");

        builder.append("Total takes: ").append(takeId-2).append("; ");
        builder.append(String.format("Total duration: %.0f ns (= %.4f ms; %.4f s ) ",
                totalDurationNs, totalDurationNs /1000/1000, totalDurationNs /1000/1000/1000));
        builder.append(String.format("Average duration: %.0f ns (= %.4f ms; %.4f s ) ",
                avgDurationNs, avgDurationNs /1000/1000, avgDurationNs /1000/1000/1000));

        return builder.toString();
    }

    public String getAllResultsString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Benchmark started @%.0f \n", takes[0]));

        for (int i = 1; i < takeId-1; i++) {
            double timestamp = takes[i];
            double previous = takes[i-1];

            double diff = timestamp-previous;

            builder.append(String.format(
                    "Take %d: " +
                            "timestamp_start: %.0f, " +
                            "timestamp_finish: %.0f, " +
                            "duration: %.0f ns (= %.4f ms; %.4f s ) ",
                    i, previous, timestamp, diff, diff/1000/1000, diff/1000/1000/1000)).append("\n");
        }

        return builder.toString();
    }




}
