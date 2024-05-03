package design.patterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer {
    String id;
    Producer(String id){
        this.id = id;
    }

    void produceMessage(String message, Channel channel) {
        channel.publishMessage("producer-"+id.concat(" "+message));
    }
}

@FunctionalInterface
interface Subscriber {
    void task(String message);
}

class Channel {

    /*
     * -> ArrayBlockingQueue is a queue of a fixed size.
     * The most important difference between LinkedBlockingQueue and ConcurrentLinkedQueue is that
     * -> if you request an element from a LinkedBlockingQueue and the queue is empty, your thread will wait until there is something there.
     * -> A ConcurrentLinkedQueue will return right away with the behavior of an empty queue.
     */
    Queue<String> q = null;
    List<Subscriber> subscriberList;

    Channel(){
        q = new LinkedBlockingQueue();
        subscriberList = new ArrayList<>();
        intiMessageThread();
    }

    private void intiMessageThread() {
        new Thread(() -> {
            while(true) {
                while(!q.isEmpty()) {
                    String message = q.poll();
                    subscriberList.parallelStream()
                            .forEach(subscriber -> subscriber.task(message));
                }
            }
        }).start();
    }

    void addSubscriberToChannel(Subscriber s) {
        subscriberList.add(s);
    }

    void publishMessage(String message) {
        q.add(message);
    }

}

public class Observer {

    public static void main(String[] args) throws InterruptedException {
        Channel newsChannel = new Channel();

        Producer producer1 = new Producer("1");
        Producer producer2 = new Producer("2");

        for(int i = 1; i < 5; i++) {
            int finalI = i;
            newsChannel.addSubscriberToChannel((message) -> {
                System.out.println("Subs "+ finalI + " || " + message);
            });
        }

        producer1.produceMessage("HELLO!!", newsChannel);

        Thread.sleep(3000);
        producer2.produceMessage("HELLO 2!!", newsChannel);

    }
}
