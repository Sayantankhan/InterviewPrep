package company;

import java.util.*;
import java.util.stream.Collectors;

class UserTuple {
    String id;
    long timestamp;

    UserTuple(String id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}


class UserVisited {
    Map<Integer, Set<String>> map;
    Map<String, Integer> userVisitMap;

    public UserVisited() {
        map = new HashMap<>();
        userVisitMap = new HashMap<>();
    }

    // O(1)
    void addUser(String id) {
        Integer time_visited = userVisitMap.getOrDefault(id, 0);
        userVisitMap.put(id, time_visited + 1);

        if(time_visited > 0) map.get(time_visited).remove(id);

        Set<String> s = map.getOrDefault(time_visited+1, new LinkedHashSet<String>());
        s.add(id);
        map.put(time_visited+1, s);
    }

    List<String> getUsersVisitedOnce(int k) {
        if(k == -1)  return map.get(1).stream().collect(Collectors.toList());
        else {
            int len = map.get(1).size();
            return new ArrayList<>(map.get(1)).subList(Math.max(0, len-k), len);
        }
    }
}

public class UBER_MostRecentUsers {

    // O(k * log n)
    static List<String> getMostRecentUsers(List<UserTuple> t, int k) {

        if(k == -1) {
            return t.stream().map(e -> e.id).collect(Collectors.toList());
        }

        PriorityQueue<UserTuple> pq = new PriorityQueue<>((u1, u2) -> Long.compare(u2.timestamp, u1.timestamp));
        pq.addAll(t);

        List<String> list = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            list.add(pq.poll().id);
        }
        return list;
    }

    public static void main(String[] args) {
        UserTuple t1 = new UserTuple("u1", 1745293380);
        UserTuple t2 = new UserTuple("u2", 1745205780);
        UserTuple t3 = new UserTuple("u3", 1745032980);

        List<UserTuple> ulist = new ArrayList<>(){{
            add(t1);
            add(t3);
            add(t2);
        }};


        UserVisited visited = new UserVisited();
        visited.addUser("u1");
        visited.addUser("u3");
        visited.addUser("u2");
        visited.addUser("u3");
        visited.addUser("u4");
        visited.addUser("u5");
        visited.addUser("u6");
        visited.addUser("u7");
        visited.addUser("u1");
        visited.addUser("u2");
        visited.addUser("u8");


        System.out.println(visited.getUsersVisitedOnce(2));


    }

}
