/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Вам даны два непустых связных списка, которые представляют два неотрицательных целых числа. Цифры хранятся в обратном порядке, и каждый узел содержит одну цифру. Сложите эти два числа и верните результат в виде связного списка.
 * 
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * 
 * Time Complexity: O(max(N, M)) — где N и M — длины списков l1 и l2.
 * Space Complexity: O(max(N, M)) — так как мы создаем новый список для хранения суммы.
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Создаем фиктивную голову для нового списка, чтобы упростить добавление узлов
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0; // Перенос в следующий разряд

        // Итерируемся, пока есть элементы в l1 ИЛИ l2 ИЛИ остался перенос (carry)
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Начинаем сумму с переноса из прошлого разряда

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next; // Двигаемся дальше по первому списку
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next; // Двигаемся дальше по второму списку
            }

            carry = sum / 10; // Вычисляем новый перенос (0 или 1)
            current.next = new ListNode(sum % 10); // Записываем остаток в новый узел
            current = current.next; // Сдвигаем указатель результата
        }

        return dummyHead.next; // Возвращаем список, пропуская фиктивную голову
    }
}

