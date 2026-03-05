package com.ratelimiter.ratelimiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateLimiterController {

    // one limiter for all users
    // max=5 tokens, refill=2/sec
    // max=5 requests per 10 seconds
    private final RateLimiter rateLimiter
            = new RateLimiter(5, 2, 5, 10000);

    // Token Bucket endpoint
    // GET /api/token/{userId}
    @GetMapping("/token/{userId}")
    public ResponseEntity<String> tokenBucket(
            @PathVariable String userId) {

        boolean allowed = rateLimiter.allowTokenBucket(userId);

        if (allowed) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("✅ ALLOWED | User: " + userId
                            + " | Tokens left: "
                            + rateLimiter.getTokensLeft(userId));
        } else {
            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("❌ BLOCKED | User: " + userId
                            + " | Too many requests!");
        }
    }

    // Sliding Window endpoint
    // GET /api/sliding/{userId}
    @GetMapping("/sliding/{userId}")
    public ResponseEntity<String> slidingWindow(
            @PathVariable String userId) {

        boolean allowed = rateLimiter.allowSlidingWindow(userId);

        if (allowed) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("✅ ALLOWED | User: " + userId
                            + " | Requests in window: "
                            + rateLimiter.getRequestCount(userId));
        } else {
            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("❌ BLOCKED | User: " + userId
                            + " | Too many requests!");
        }
    }

    // Status endpoint
    // GET /api/status/{userId}
    @GetMapping("/status/{userId}")
    public ResponseEntity<String> status(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                "User: " + userId
                        + " | Tokens: "
                        + rateLimiter.getTokensLeft(userId)
                        + " | Window Requests: "
                        + rateLimiter.getRequestCount(userId)
        );
    }
}