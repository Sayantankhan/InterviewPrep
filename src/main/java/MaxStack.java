import java.util.LinkedList;
import java.util.TreeMap;

public class MaxStack {
    // Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
    // Stack Findmax o(1)

    static class Node {
        public Node prev, next;
        public int value;

        public Node(int val){
            this.value = val;
        }
    }

    final TreeMap<Integer, LinkedList<Node>> map = new TreeMap<>();
    final Node head = new Node(0);

    public MaxStack() {
        head.next = head.prev = head;
    }

    public void push(int x) {
        Node node = new Node(x);
        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
        map.computeIfAbsent(x, k -> new LinkedList<>()).add(node);
    }

    public int pop() {
        Node tail = head.prev;
        if (tail == head) {
            return 0;   // no element exist
        }

        tail.prev.next = tail.next;
        tail.next.prev = tail.prev;

        map.get(tail.value).removeLast();
        if(map.get(tail.value).isEmpty()) map.remove(tail.value);

        return tail.value;
    }

    public int top() {
        Node tail = head.prev;
        return tail.value;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        Node node = map.get(max).removeLast();

        node.prev.next = node.next;
        node.next.prev = node.prev;

        if(map.get(max).isEmpty()) {
            map.remove(max);
        }

        return max;
    }
}
