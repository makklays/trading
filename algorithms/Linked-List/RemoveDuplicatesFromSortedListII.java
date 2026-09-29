/**
 * You are given the head of a sorted linked list.
 * Delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 *
 *
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node helps handle cases where the head itself has duplicates
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 'prev' is the last known node with a completely unique value
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            // Check if the next two nodes have duplicate values
            if (prev.next.val == prev.next.next.val) {
                int duplicateVal = prev.next.val;
                // Move forward to skip ALL nodes with this duplicate value
                while (prev.next != null && prev.next.val == duplicateVal) {
                    prev.next = prev.next.next;
                }
            } else {
                // No duplicate detected, safely move 'prev' forward
                prev = prev.next;
            }
        }
        
        return dummy.next;
    }
}

