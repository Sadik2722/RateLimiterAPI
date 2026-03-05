package com.ratelimiter.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class RateLimiter {

    // ConcurrentHashMap → thread safe
    private final Map<String, TokenBucket> tokenBuckets
            = new ConcurrentHashMap<>();

    private final Map<String, SlidingWindow> slidingWindows
            = new ConcurrentHashMap<>();

    private final int maxTokens;
    private final int refillRate;
    private final int maxRequests;
    private final long windowSizeMs;

    public RateLimiter(int maxTokens, int refillRate,
                       int maxRequests, long windowSizeMs) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    private TokenBucket getBucket(String userId) {
        tokenBuckets.putIfAbsent(userId,
                new TokenBucket(maxTokens, refillRate));
        return tokenBuckets.get(userId);
    }

    private SlidingWindow getWindow(String userId) {
        slidingWindows.putIfAbsent(userId,
                new SlidingWindow(maxRequests, windowSizeMs));
        return slidingWindows.get(userId);
    }

    public boolean allowTokenBucket(String userId) {
        return getBucket(userId).allowRequest();
    }

    public boolean allowSlidingWindow(String userId) {
        return getWindow(userId).allowRequest();
    }
    public int getTokensLeft(String userId) {
        return getBucket(userId).getCurrentTokens();
    }

    public int getRequestCount(String userId) {
        return getWindow(userId).getRequestCount();
    }

    public void printStatus(String userId) {
        System.out.println("  User            : " + userId);
        System.out.println("  Tokens left     : "
                + getBucket(userId).getCurrentTokens());
        System.out.println("  Requests in win : "
                + getWindow(userId).getRequestCount());
    }
}