import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Producer {
    String id;
    DelayQueue dq;
    Producer(String id, DelayQueue dq) {
        this.dq = dq;
        this.id = id;
    }

    boolean publishMessage(DelayMessage dm) {
        try {
            dq.publishMessage(dm);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

class Consumer {
    final String id;
    final DelayQueue dq;

    Consumer(String id, DelayQueue dq) {
        this.id = id;
        this.dq = dq;
    }

    void consumeMessage() throws InterruptedException {
        while(true) {
            DelayMessage dm = dq.consumeMessage();
            System.out.println(System.currentTimeMillis() + " " + id + " consumed: " + " "+ dm.messageBody + " - "+dm.timestamp);
        }
    }
}

class DelayMessage {
    String messageId;
    Map<String, String> headers;
    String messageBody;
    long timestamp;

    DelayMessage(String messageId, String messageBody, Map<String, String> headers, long timestamp) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.headers = headers;
        this.timestamp = timestamp;
    }
}

public class DelayQueue {
    // - Time bound execution of message
    // - Queue system

    final PriorityQueue<DelayMessage> pq;
    final Lock lock;
    final ArrayList<Consumer> consumer;
    final Condition notEmpty;

    DelayQueue() {
        this.pq = new PriorityQueue<>((pq1, pq2) -> Long.compare(pq1.timestamp, pq2.timestamp));
        this.lock = new ReentrantLock();
        this.consumer = new ArrayList<Consumer>();
        notEmpty = lock.newCondition();
    }
    public void publishMessage(DelayMessage dm) {
        lock.lock();
        try {
            pq.offer(dm);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public DelayMessage consumeMessage() throws InterruptedException {
        lock.lock();
        try{
            while (true) {
                if(pq.isEmpty()) {
                    notEmpty.await();
                } else {
                    DelayMessage dm = pq.peek();
                    long now = System.currentTimeMillis();
                    if(dm.timestamp < now) {
                        return pq.poll();
                    }
                }

            }
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DelayQueue dq = new DelayQueue();

        Producer pq = new Producer("producer-"+ UUID.randomUUID(), dq);
        pq.publishMessage(new DelayMessage("4444", "Hello message 4", null, -1));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Producer pq = new Producer("producer-"+ UUID.randomUUID().toString(), dq);
                for (int i = 1; i <= 3; i++) {
                    pq.publishMessage(new DelayMessage(pq.id, "Hello message from "+pq.id, null, System.currentTimeMillis()+ (10_000L * i)));
                }

            }
        };

        for(int i = 1; i < 5; i++) {
            new Thread(runnable).start();
        }

        Consumer consumer1 = new Consumer("1", dq);
        Consumer consumer2 = new Consumer("2", dq);

        new Thread(() -> {
            try {
                consumer1.consumeMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                consumer2.consumeMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


        while(true) {}

    }


}
