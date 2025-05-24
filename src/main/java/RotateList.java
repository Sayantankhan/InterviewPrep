class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class RotateList {

    // Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null ) return head;

        int len = 0;
        ListNode temp = head, currNode = head;
        while(temp != null) {
            temp = temp.next;
            len ++;
        }

        k = (k > len) ? k%len : k;
        for(int i = 0; i < k; i++) {
            currNode = currNode.next;
        }
        if(k == 0 || currNode == null) return head;

        ListNode rem = head;

        while(currNode.next != null) {
            currNode = currNode.next;
            rem = rem.next;
        }

        ListNode node = rem.next;
        rem.next = null;
        currNode.next = head;


        return node;
    }
}
