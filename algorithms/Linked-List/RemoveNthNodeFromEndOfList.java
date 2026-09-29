/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * 
 *
 * @author Alexander Kuziv <makklays@gmail.com> 
 */ 

/*class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}*/
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node is crucial handles cases like removing the head node itself
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // 1. Move the fast pointer n + 1 steps ahead
        // This creates a gap of n nodes between slow and fast
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 2. Move both pointers together until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // 3. Skip the n-th node from the end
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}

