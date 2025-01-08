import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SlidingWindowAlgo implements RateLimiter {
    private final int maxRequests;
    private final long windowSize;
    private Map<String, Queue<Long>> requestTimeStamp;

    public SlidingWindowAlgo(int maxRequests, long windowSize) {
        this.maxRequests = maxRequests;
        this.windowSize = windowSize;
    }


    @Override
    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        requestTimeStamp.putIfAbsent(clientId, new LinkedList<>());

        Queue<Long> timeStamp = requestTimeStamp.get(clientId);
        while (!timeStamp.isEmpty() && currentTime - timeStamp.peek() > windowSize) {
            timeStamp.poll();
        }

        if (timeStamp.size() < maxRequests) {
            timeStamp.add(currentTime);
            requestTimeStamp.put(clientId, timeStamp);
            return true;
        }
        return false;
    }
}
