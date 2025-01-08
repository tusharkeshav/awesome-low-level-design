public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter fixedWindowRateLimiter = RateLimiterFactory.createRateLimiter(RateLimiterType.FIXED_WINDOW, 5, 6000);
        RateLimiterManager rateLimiterManager = RateLimiterManager.getInstance(fixedWindowRateLimiter);

        System.out.println("Fixed Window Rate Limiter:");
        for (int i = 0; i < 12; i++) {
            System.out.println(fixedWindowRateLimiter.allowRequest("client1"));
        }
//        rateLimiterManager.allowRequest("123");
//        rateLimiter.allowRequest("123");
//        rateLimiter.allowRequest("234");
    }
}
