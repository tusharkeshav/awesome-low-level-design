import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowAlgo implements RateLimiter {
    private final int maxAllowRequests;
    private final long windowSizeTime;
    private Map<String, Integer> requestCount;
    private Map<String, Long> windowStartTime;

    public FixedWindowAlgo(int maxAllowRequests, long windowSizeTime) {
        this.maxAllowRequests = maxAllowRequests;
        this.windowSizeTime = windowSizeTime;
        this.requestCount = new HashMap<>();
        this.windowStartTime = new HashMap<>();
    }

    @Override
    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        windowStartTime.putIfAbsent(clientId, currentTime); // first time request
        requestCount.putIfAbsent(clientId, 0); // first time request count

        long windowTime = windowStartTime.get(clientId); // if window gets beyond threshold
        if (currentTime - windowTime > windowSizeTime) {
            windowStartTime.put(clientId, currentTime);
            requestCount.put(clientId, 0);
        }

        int requestCountForClient = requestCount.get(clientId);
        if (requestCountForClient < maxAllowRequests) {
            requestCount.put(clientId, requestCountForClient + 1);
            return true;
        }
        return false;
    }
}
