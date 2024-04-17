package custom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/*
 * Requirements
 * Thread Pool with Fix sizes
 * Assign tasks to the threads
 * Run the threads
 */

@FunctionalInterface
interface Task {
    Object task() throws InterruptedException;
}
public class CustomThreadPool {

    private static CustomThreadPool threadPool;
    private int size;
    /*
     * -> ArrayBlockingQueue is a queue of a fixed size.
     * The most important difference between LinkedBlockingQueue and ConcurrentLinkedQueue is that
     * -> if you request an element from a LinkedBlockingQueue and the queue is empty, your thread will wait until there is something there.
     * -> A ConcurrentLinkedQueue will return right away with the behavior of an empty queue.
     */
    ConcurrentLinkedQueue<FutureTask> tasks = null;
    private List<Thread> threads;
    private volatile Boolean hasShutDown = false; // volatile helps the variable change by one thread reflect to other
    private final String MUTEX = "LOCK-MUTEX";

    private CustomThreadPool(int size) {
        this.size = size;
        this.tasks = new ConcurrentLinkedQueue<FutureTask>();
        threads = new ArrayList<>();

        startTaskFromQueue();
    }

    private void startTaskFromQueue() {
        for(int i = 1; i <= size; i++) {
            threads.add(new Thread(() -> {
                while(true) {
                    while(!tasks.isEmpty()) {
                        FutureTask f = (FutureTask) tasks.poll();
                        if(f != null) {
                            f.run();
                        }
                    }
                    if(hasShutDown) {
                        break;
                    }
                }
            }));
        }
        threads.parallelStream().forEach(thread -> thread.start());
    }

    public static CustomThreadPool fixedThreadPool(int size){
        if(threadPool == null) {
            threadPool = new CustomThreadPool(size);
        }
        return threadPool;
    }

    public Future<?> submitTask(Task task) throws Exception {
        if(hasShutDown) {
            throw new Exception("Pool has been shutdown");
        }
        FutureTask f = new FutureTask(() -> task.task());
        tasks.add(f);
        return (Future) f;
    }

    public List<Future<?>> submitTasks(List<Task> tsks) throws Exception {
        List<Future<?>> futureList = new ArrayList<>();
        if(hasShutDown) {
            throw new Exception("Pool has been shutdown");
        }
        for(Task task : tsks) {
            FutureTask f = new FutureTask(() -> task.task());
            tasks.add(f);
            futureList.add((Future) f);
        }
        return futureList;
    }

    public void shutDown() {
        if(!hasShutDown) {
            synchronized (MUTEX) {
                if(!hasShutDown) {
                    hasShutDown = true;
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        CustomThreadPool threadPool = CustomThreadPool.fixedThreadPool(5);

        Future f1 = threadPool.submitTask(() -> {
            System.out.println("F1 -> Hello world");
            Thread.sleep(3000);
            return "F1 -> Done";
        });

        Future f2 = threadPool.submitTask(() -> {
            System.out.println("F2 -> Hello world");
            Thread.sleep(2000);
            return "F2 -> Done";
        });

        Future f3 = threadPool.submitTask(() -> {
            System.out.println("F3 -> Hello world");
            Thread.sleep(3000);
            return "F3 -> Done";
        });

        Future f4 = threadPool.submitTask(() -> {
            System.out.println("F4 -> Hello world");
            Thread.sleep(3000);
            return "F4 -> Done";
        });


        System.out.println("****************** Making Thread to sleep ******************");
        Thread.sleep(3000);
        System.out.println("****************** Checking Result ******************");
        threadPool.shutDown();

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f4.get());

        // you cant add new task once pool is shutdown !!! It will throw exception
//        Future f5 = threadPool.submitTask(() -> {
//            System.out.println("F5 -> Hello world");
//            Thread.sleep(3000);
//            return "F5 -> Done";
//        });
    }

}
