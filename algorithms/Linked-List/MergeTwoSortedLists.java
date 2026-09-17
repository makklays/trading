/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Time Complexity: O(n*m) 
 * Space Complexity: O(1) 
 *
 * @author Alexander Kuziv <makklays@gmail.com> 
 */ 

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Создаем фиктивную голову для нового списка
        ListNode dummy = new ListNode(0);
        // Указатель для сборки нового списка
        ListNode current = dummy;
        
        // Бежим по обоим спискам, пока они оба не станут null
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1; // Прицепляем узел из list1
                list1 = list1.next;   // Двигаем указатель list1
            } else {
                current.next = list2; // Прицепляем узел из list2
                list2 = list2.next;   // Двигаем указатель list2
            }
            current = current.next;   // Сдвигаем собирающий указатель
        }
        
        // Если один из списков закончился, просто подвешиваем остаток второго
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        // Возвращаем реальное начало объединенного списка (пропуская dummy)
        return dummy.next;
    }
}

