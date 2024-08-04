package company.google;

import java.util.*;

//https://leetcode.com/problems/stock-price-fluctuation/
public class StockPriceFluctuation {

    class Tuple {
        int price;
        int timestamp;

        Tuple(int price, int timestamp) {
            this.price = price;
            this.timestamp = timestamp;
        }
    }

    Map<Integer, Integer> priceMap;
    int price;
    int last_timestamp;

    Comparator<Tuple> minComparator = (e1, e2) -> e1.price-e2.price;
    Comparator<Tuple> maxComparator = (e1, e2) -> e2.price-e1.price;

    PriorityQueue<Tuple> minPq;
    PriorityQueue<Tuple> maxPq;

    public StockPriceFluctuation() {
        priceMap = new HashMap<Integer, Integer>();

        this.minPq = new PriorityQueue<Tuple>(minComparator);
        this.maxPq = new PriorityQueue<Tuple>(maxComparator);
    }

    public void update(int timestamp, int price) {
        priceMap.put(timestamp, price);
        minPq.offer(new Tuple(price, timestamp));
        maxPq.offer(new Tuple(price, timestamp));

        if(last_timestamp <= timestamp) {
            this.last_timestamp = timestamp;
            this.price = price;
        }

        while(priceMap.get(maxPq.peek().timestamp) != maxPq.peek().price) {
            maxPq.poll();
        }

        while(priceMap.get(minPq.peek().timestamp) != minPq.peek().price) {
            minPq.poll();
        }

    }

    public int current() {
        return this.price;
    }

    public int maximum() {
        Tuple max = this.maxPq.peek();
        return max == null ? 0 : max.price;
    }

    public int minimum() {
        Tuple min = this.minPq.peek();
        return min == null ? 0 : min.price;
    }
/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
    public static void main(String[] args) {
        StockPriceFluctuation sf = new StockPriceFluctuation();
        sf.update(1, 10);
        sf.update(2, 5);
        System.out.println(sf.current());
        System.out.println(sf.maximum());
        sf.update(1, 3);
        System.out.println(sf.maximum());
        sf.update(4,2);
        System.out.println(sf.minimum());
    }
}
