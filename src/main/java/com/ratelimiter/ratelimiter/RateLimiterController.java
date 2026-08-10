package com.ratelimiter.ratelimiter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateLimiterController {

    private final RateLimiter rateLimiter
            = new RateLimiter(5, 2, 5, 10000);

    @GetMapping("/token/{userId}")
    public ResponseEntity<String> tokenBucket(
            @PathVariable String userId) {
        boolean allowed = rateLimiter.allowTokenBucket(userId);
        if (allowed) {
            return ResponseEntity.status(HttpStatus.OK)
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

    @GetMapping("/sliding/{userId}")
    public ResponseEntity<String> slidingWindow(
            @PathVariable String userId) {
        boolean allowed = rateLimiter.allowSlidingWindow(userId);
        if (allowed) {
            return ResponseEntity.status(HttpStatus.OK)
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

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("✅ Rate Limiter API is Running!");
    }
}
