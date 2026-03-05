package com.ratelimiter.ratelimiter;

public class TokenBucket {

    private final int maxTokens;
    private final int refillRate;
    private int currentTokens;
    private long lastRefillTime;

    public TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.currentTokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long timeElapsed = now - lastRefillTime;
        int tokensToAdd = (int) (timeElapsed / 1000) * refillRate;

        if (tokensToAdd > 0) {
            currentTokens = Math.min(maxTokens, currentTokens + tokensToAdd);
            lastRefillTime = now;
        }
    }

    public synchronized boolean allowRequest() {
        refill();
        if (currentTokens > 0) {
            currentTokens--;
            return true;   // ✅ allowed
        }
        return false;      // ❌ blocked
    }

    public int getCurrentTokens() {
        return currentTokens;
    }
}

