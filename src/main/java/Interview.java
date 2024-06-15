import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interview implements Runnable{

    volatile int index = 0;
    final String MUTEX = "mutex";

    static final Lock lock = new ReentrantLock();

    static class NumberRunnable implements Runnable {
        int index = 0;

        @Override
        public void run() {
            while(index < 100) {
                try{
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " " +index);
                    index++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Override
    public void run() {
        while(index < 100) {
            synchronized (MUTEX) {
                System.out.println(Thread.currentThread().getName() + " " +index);
                index++;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public int calculate(String s) {
        int len = s.length();

        if(len > 100) return 0;

        int[] count = new int[100];
        for(int i = 0; i < len-1; i++) {
            int index = Integer.parseInt(s.substring(i, i+2));
            count[index] = 1;
        }

        return Arrays.stream(count).sum();
    }

    public String solution() {
        StringBuilder sb = new StringBuilder();
        for(int i = 10; i < 50; i++) {
            sb.append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {

//        List<Thread> t = new ArrayList<>();
//        //Interview interview = new Interview();
//        NumberRunnable runnable = new NumberRunnable();
//
//        for(int i = 0; i < 3; i++) {
//            int finalI = i;
//            t.add(new Thread(() -> runnable.run()));
//        }

//        CountDownLatch latch = new CountDownLatch(4);
//        latch.countDown();
//        // latch.await(5, TimeUnit.SECONDS);
//        latch.await();

        System.out.println(new Interview().calculate(new Interview().solution()));
    }

}




