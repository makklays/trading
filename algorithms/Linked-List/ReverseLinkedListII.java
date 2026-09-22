/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * 
 * Алгоритм:
 *  1. Создаем dummy-узел, который указывает на head.
 *  2. Доходим до узла, предшествующего позиции left (назовем его prev).
 *  3. Поочередно перемещаем элементы с позиции left + 1 до right в начало разворачиваемого отрезка, меняя указатели на месте (in-place).
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * 
 * Time Complexity: O(n) 
 * Space Complexity: O(1) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com> 
 */

/**
 * Определение односвязного списка:
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        // Фиктивный узел для упрощения работы с head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1. Находим узел перед левой границей (prev)
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 2. Указатель на первый разворачиваемый узел
        ListNode start = prev.next; 
        // 3. Указатель на следующий узел, который будет перемещаться вперед
        ListNode then = start.next; 

        // 4. Переворачиваем подсписок
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}

