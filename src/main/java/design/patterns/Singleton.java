package design.patterns;

import util.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Singleton {

    private static Singleton instance;
    private static String MUTEX = "";

    private Singleton() {}

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (MUTEX) {
                if(instance == null) {
                    System.out.println("Creating Instance For Singleton");
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }


    public static void main(String[] args) throws Exception {
        // Singleton pattern : It will have only one instance!!
        Set<Singleton> list = new HashSet<>();
        List<Thread> threadList = new ArrayList<Thread>();

        IntStream.range(0, 100).forEach(i -> threadList.add(new Thread(() -> list.add(Singleton.getInstance()))));
        threadList.parallelStream().forEach(thread -> thread.run());

        Utility.assertTrue(list.size(), 1);
    }
}

