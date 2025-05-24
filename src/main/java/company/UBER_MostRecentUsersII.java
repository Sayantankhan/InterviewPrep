package company;

import java.util.*;

class User {
    String userId;
    int visited;
    long timestamp;

    User(String userId) {
        this.userId = userId;
    }
}


class MostRecentUser {
    // Top-K heap and map
    private final int k;
    private final PriorityQueue<User> topKHeap;
    private final Map<String, User> topKMap;

    MostRecentUser(int k) {
        this.k = k;

        this.topKHeap = new PriorityQueue<User>(
                Comparator
                        .comparingInt((User q) -> -q.visited)
                        .thenComparingLong(q -> -q.timestamp) // reverse timestamp
        );
        this.topKMap = new HashMap<>();
    }

    public void addQuery(String query) {

        long ts = System.currentTimeMillis();

        if(topKMap.containsKey(query)) {
            User user = topKMap.get(query);
            user.visited += 1;
            if(user.visited > 1) {
                topKHeap.remove(user);
            }
        } else{
            User user = new User(query);
            user.visited = 1;
            user.timestamp = ts;
            topKMap.put(query, user);

            if(topKHeap.size() >= k) topKHeap.poll();
            topKHeap.add(user);
        }
    }

    public List<User> mostRecentUser() {
        List<User> result = new ArrayList<>(topKHeap);
        return result;
    }
}

public class UBER_MostRecentUsersII {


    public static void main(String[] args) {
        MostRecentUser mru = new MostRecentUser(10);
        List<String> us = List.of("u1", "u2", "u3", "u4", "u5", "u6", "u7", "u2", "u3","u4", "u5","u9", "u11","u12", "u20","u21", "u30", "u31", "u32", "u33");
        for(String userId : us) {
            mru.addQuery(userId);
        }

        System.out.println("Top-K Search Queries:");
        for (User qc : mru.mostRecentUser()) {
            System.out.println(qc.userId);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
    }
}
