/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * 
 * Time Complexity: O(n) 
 * Space Complexity: O(1) 
 *
 * 
 */ 

/*class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}*/
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Фиктивный узел для удобного отслеживания нового начала списка
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 1. Поиск k-го узла текущей группы
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // Если осталось меньше k узлов, завершаем работу
            }
            ListNode groupNext = kth.next;

            // 2. Разворот текущей группы из k узлов
            ListNode prev = groupNext; 
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 3. Подключение развернутой группы обратно в список
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp; // Перемещаем указатель в конец обработанной группы
        }

        return dummy.next;
    }

    // Вспомогательный метод для поиска k-го узла от текущей позиции
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}

