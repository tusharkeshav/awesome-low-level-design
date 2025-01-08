public class RateLimiterManager {
    private static RateLimiterManager instance;
    private RateLimiter rateLimiter;

    private RateLimiterManager(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public static synchronized RateLimiterManager getInstance(RateLimiter rateLimiter) {
        if (instance == null) {
            instance = new RateLimiterManager(rateLimiter);
        }
        return instance;
    }

    public boolean allowRequest(String client) {
        return rateLimiter.allowRequest(client);
    }
}
