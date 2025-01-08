public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterType type, int maxRequests, long windowSizeInMillis) {
        switch (type) {
            case FIXED_WINDOW:
                return new FixedWindowAlgo(maxRequests, windowSizeInMillis);
            case SLIDING_WINDOW:
                return new SlidingWindowAlgo(maxRequests, windowSizeInMillis);
            default:
                throw new RuntimeException("Rate limiter not found");
        }
    }
}
