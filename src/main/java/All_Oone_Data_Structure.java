import java.util.*;

public class AllOne {

    // Given a stream of events with contentId and its popularity trend (+1/-1) we need to provide the most popular contentId at any
    static class Node{
        Node prev, next;
        String val;
        int freq;

        Node(String val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    final Node head, tail;
    final Map<String, Node> map;

    public AllOne() {
        head = new Node("", -1);
        tail = new Node("", -1);

        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    void getNextPos(Node node, Node tail) {
        Node pos = node.prev;
        Node temp = node;

        while(temp.next != tail && node.freq > temp.next.freq) {
            temp = temp.next;
        }

        if(temp != node) {
            // swap
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = temp.next;
            temp.next.prev = node;
            node.prev = temp;
            temp.next = node;
        }

    }

    void getPrevPos(Node node,Node head) {
        Node temp = node;
        while(temp.prev != head && node.freq < temp.prev.freq) {
            temp = temp.prev;
        }

        // temp->prev node temp
        if(temp != node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = temp;
            node.prev = temp.prev;
            temp.prev.next = node;
            temp.prev = node;
        }
    }

    public void inc(String key) {
        Node e_node = map.getOrDefault(key, null);
        if(e_node != null) {
            e_node.freq = e_node.freq + 1;
            getNextPos(e_node, tail);
        } else {
            Node node = new Node(key, 1);
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;

            map.put(key, node);
        }
    }

    public void dec(String key) {
        Node e_node = map.getOrDefault(key, null);
        if(e_node != null) {
            e_node.freq = e_node.freq - 1;
            if (e_node.freq == 0) {
                // remove from dll
                e_node.prev.next = e_node.next;
                e_node.next.prev = e_node.prev;
                // remove from map
                map.remove(key);

            } else {
                getPrevPos(e_node, head);
            }
        }
    }

    public String getMaxKey() {
        if(tail.prev != head) return tail.prev.val;
        else return "";
    }

    public String getMinKey() {
        if(head.next != tail) return head.next.val;
        else return "";
    }
}
