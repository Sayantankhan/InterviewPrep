package company;

import java.util.*;

/*
i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
 */

// https://leetcode.com/problems/jump-game-iv/description/
public class JumpGameIV {

    public int minJumps(int[] arr) {
        // create hash table
        HashMap<Integer, Set> map = new HashMap<>();

        // for all the equal
        for(int i = 0; i < arr.length; i++) {
            if(i != 0 && i != arr.length-1 && arr[i-1] == arr[i] && arr[i] == arr[i+1]) continue; // just to skip xxxxxxxxxxxxxx ; eating memory
            Set<Integer> set = map.getOrDefault(arr[i], new LinkedHashSet<Integer>());
            set.add(i);
            map.put(arr[i], set);
        }

        boolean[] visited = new boolean[arr.length];
        // BFS - using same concept of min len of an un-directed graph - level by level traversing
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;
        int count = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                int index = q.poll();
                size--;
                visited[index] = true;
                if(index == arr.length-1) return count;
                else {
                    Set<Integer> set = map.get(arr[index]);
                    for(Integer i : set) {
                        if(!visited[i]) {
                            q.add(i);
                        }
                    }

                    // index+1
                    if(index+1 < arr.length && !visited[index+1]) q.add(index+1);
                    // index-1
                    if(index-1 >= 0 && !visited[index-1]) q.add(index-1);
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGameIV().minJumps(new int[] {100,-23,-23,404,100,23,23,23,3,404}));
//        System.out.println(new JumpGameIV().minJumps(new int[] {1, 51, 51, 51, 51, -47, -5, -47, 48 }));
//        System.out.println(new JumpGameIV().minJumps(new int[] {51,64,-15,58,98,31,48,72,78,-63,92,-5,64,-64,51,-48,64,48,-76,-86,-5,-64,-86,-47,92,-41,58,72,31,78,-15,-76,72,-5,-97,98,78,-97,-41,-47,-86,-97,78,-97,58,-41,72,-41,72,-25,-76,51,-86,-65,78,-63,72,-15,48,-15,-63,-65,31,-41,95,51,-47,51,-41,-76,58,-81,-41,88,58,-81,88,88,-47,-48,72,-25,-86,-41,-86,-64,-15,-63}));
//        System.out.println(new JumpGameIV().minJumps(new int[] {7,6,9,6,9,6,9,7}));




        Map<Integer, List> map = new HashMap<>();
        map.putIfAbsent(1, new ArrayList() {{add(2);}});

        System.out.println(map);
        map.get(1).add(6);
        System.out.println(map);
    }
}
