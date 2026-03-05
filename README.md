# API Rate Limiter 🚦

A thread-safe API Rate Limiter built in Java with Spring Boot,
supporting two industry-standard algorithms.

## 🚀 Live Demo
- Token Bucket  → https://ratelimiterapi-1.onrender.com/api/token/Sadik
- Sliding Window→ https://ratelimiterapi-1.onrender.com/api/sliding/Sadik
- Status        → https://ratelimiterapi-1.onrender.com/api/status/Sadik

## 📌 What is Rate Limiting?
Rate Limiting controls how many requests a user can make
in a given time period. Used by AWS, GitHub, Twitter, Stripe
to protect their APIs from abuse.

## ⚙️ Algorithms Implemented

### 1. Token Bucket
- Each user gets a bucket with N tokens
- Every request consumes 1 token
- Tokens refill automatically over time
- Allows small bursts of traffic
- Used by: AWS, Stripe

### 2. Sliding Window
- Tracks timestamps of last N requests
- Removes expired timestamps outside window
- Strict limit — no bursts allowed
- Used by: GitHub API, Twitter API

## 🔥 Features
- Per-user rate limiting (each user gets independent limit)
- Thread-safe using ConcurrentHashMap + synchronized blocks
- Supports 1000+ concurrent users
- O(1) time complexity per request
- Deployed as REST API on cloud
- Dockerized for easy deployment

## 🛠️ Tech Stack
- Java 21
- Spring Boot 3.2
- Docker
- REST API
- ConcurrentHashMap
- Multithreading

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/token/{userId} | Token Bucket check |
| GET | /api/sliding/{userId} | Sliding Window check |
| GET | /api/status/{userId} | Check user status |

## 📤 Sample Response

### Allowed (200 OK)# API Rate Limiter 🚦

A thread-safe API Rate Limiter built in Java with Spring Boot,
supporting two industry-standard algorithms.

## 🚀 Live Demo
- Token Bucket  → https://ratelimiterapi-1.onrender.com/api/token/Sadik
- Sliding Window→ https://ratelimiterapi-1.onrender.com/api/sliding/Sadik
- Status        → https://ratelimiterapi-1.onrender.com/api/status/Sadik

## 📌 What is Rate Limiting?
Rate Limiting controls how many requests a user can make
in a given time period. Used by AWS, GitHub, Twitter, Stripe
to protect their APIs from abuse.

## ⚙️ Algorithms Implemented

### 1. Token Bucket
- Each user gets a bucket with N tokens
- Every request consumes 1 token
- Tokens refill automatically over time
- Allows small bursts of traffic
- Used by: AWS, Stripe

### 2. Sliding Window
- Tracks timestamps of last N requests
- Removes expired timestamps outside window
- Strict limit — no bursts allowed
- Used by: GitHub API, Twitter API

## 🔥 Features
- Per-user rate limiting (each user gets independent limit)
- Thread-safe using ConcurrentHashMap + synchronized blocks
- Supports 1000+ concurrent users
- O(1) time complexity per request
- Deployed as REST API on cloud
- Dockerized for easy deployment

## 🛠️ Tech Stack
- Java 21
- Spring Boot 3.2
- Docker
- REST API
- ConcurrentHashMap
- Multithreading

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/token/{userId} | Token Bucket check |
| GET | /api/sliding/{userId} | Sliding Window check |
| GET | /api/status/{userId} | Check user status |

## 📤 Sample Response

### Allowed (200 OK)
