/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Алгоритм:
 *  1. Обрабатываем крайние случаи (список пуст, состоит из 1 узла или $k = 0$).
 *  2. Проходим по списку, чтобы посчитать его длину length и найти последний узел (tail).
 *  3. Нормализуем $k$: так как сдвиг на длину списка length возвращает его в исходное состояние, делаем k = k % length. Если новый k == 0, сдвиг не требуется.
 *  4. Замыкаем список в кольцо: tail.next = head.
 *  5. Находим новый хвост списка: он находится на позиции length - k от начала.
 *  6. Разрываем кольцо в этой точке и возвращаем новый head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Считаем длину списка и находим последний узел
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Оптимизируем k с помощью остатка от деления
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 3. Замыкаем список в кольцо
        tail.next = head;

        // 4. Ищем новый хвост: (length - k) шагов от старого начала
        int stepsToNewTail = length - k;
        ListNode newTail = tail;
        while (stepsToNewTail > 0) {
            newTail = newTail.next;
            stepsToNewTail--;
        }

        // 5. Определяем новый head и разрываем кольцо
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}

