import java.util.*;

public class MinStack {

    class Node {
        Node prev, next;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    Node head = null;
    TreeMap<Integer, LinkedList<Node>> map = null;

    public MinStack() {
        head = new Node(-1);
        head.prev = head.next = head;
        map = new TreeMap<>();
    }

    public void push(int val) {
        Node node = new Node(val);

        node.prev = head.prev;
        node.next = head;
        head.prev.next = node;
        head.prev = node;

        map.computeIfAbsent(val, k -> new LinkedList<Node>()).add(node);
    }

    public void pop() {
        Node node = head.prev;
        if(node == head) return;

        node.prev.next = head;
        head.prev = node.prev;

        map.get(node.val).removeLast();
        if(map.get(node.val).isEmpty()) map.remove(node.val);
    }

    public int top() {
        return head.prev.val;
    }

    public int getMin() {
        return map.firstKey();
    }
}
