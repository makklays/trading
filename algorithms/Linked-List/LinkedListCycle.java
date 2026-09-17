/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * 
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Если список пустой или в нем всего один элемент, цикла быть не может
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head; // Черепаха
        ListNode fast = head; // Заяц
        
        // Двигаемся, пока Заяц и его следующий шаг не упрутся в конец списка
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 1 шаг
            fast = fast.next.next;   // 2 шага
            
            // Если они встретились — цикл обнаружен!
            if (slow == fast) {
                return true;
            }
        }
        
        // Если вышли из цикла, значит Заяц добежал до конца (null) -> цикла нет
        return false;
    }
}

/*
// 1. Создаем сами узлы (пока они изолированы, next у всех равен null)
ListNode node0 = new ListNode(3);  // Индекс 0
ListNode node1 = new ListNode(2);  // Индекс 1 (сюда замкнется цикл)
ListNode node2 = new ListNode(0);  // Индекс 2
ListNode node3 = new ListNode(-4); // Индекс 3

// 2. Связываем их последовательно по стрелочкам
node0.next = node1; // 3 -> 2
node1.next = node2; // 2 -> 0
node2.next = node3; // 0 -> -4

// 3. Реализуем условие pos = 1 (зацикливаем хвост на узел с индексом 1)
node3.next = node1; // -4 -> 2

// Назначаем голову списка
ListNode head = node0;

// 4. Передаем эту голову в твой метод
Solution solution = new Solution();
boolean result = solution.hasCycle(head);

System.out.println("Есть ли цикл? " + result); // Выведет true
*/

