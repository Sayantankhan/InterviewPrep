package list;

public class RemoveDuplicateFromSortedLinkedList {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    // Remove Duplicates from Sorted List II
    // 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5  ---  1 -> 2 -> 5
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode temp = head;
        ListNode prev = null;

        while(temp != null) {
            if(temp.next != null && temp.val == temp.next.val) {

                int val = temp.val;
                while(temp != null && temp.val == val) {
                    temp = temp.next;
                }

                if(prev == null) {
                    head = temp;
                } else {
                    prev.next = temp;
                }


            } else {
                prev = temp;
                temp = temp.next;
            }
        }

        return head;
    }
}
