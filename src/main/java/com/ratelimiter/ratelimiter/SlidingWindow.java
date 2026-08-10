package com.ratelimiter.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow {

    private final int maxRequests;
    private final long windowSizeMs;
    private final Queue<Long> timestamps;

    public SlidingWindow(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.timestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() &&
                now - timestamps.peek() > windowSizeMs) {
            timestamps.poll();
        }
        if (timestamps.size() < maxRequests) {
            timestamps.add(now);
            return true;
        }
        return false;
    }

    public int getRequestCount() {
        return timestamps.size();
    }
}
