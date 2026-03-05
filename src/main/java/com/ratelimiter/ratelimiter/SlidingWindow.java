package com.ratelimiter.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow {

    private final int maxRequests;      // max requests allowed
    private final long windowSizeMs;    // window size in ms
    private final Queue<Long> timestamps; // timestamps of each request

    public SlidingWindow(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.timestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        // remove old timestamps outside the window
        while (!timestamps.isEmpty() &&
                now - timestamps.peek() > windowSizeMs) {
            timestamps.poll();
        }

        if (timestamps.size() < maxRequests) {
            timestamps.add(now);
            return true;    // ✅ allowed
        }

        return false;       // ❌ blocked
    }

    public int getRequestCount() {
        return timestamps.size();
    }
}
