/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *   val: an integer representing Node.val
 *   random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * 
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) 
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Шаг 1: Создаем копии узлов и вставляем их сразу после оригиналов
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            Node copyNode = new Node(curr.val);
            
            curr.next = copyNode;
            copyNode.next = nextNode;
            
            curr = nextNode;
        }
        
        // Шаг 2: Настраиваем random указатели для скопированных узлов
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // curr.next — это копия текущего узла.
                // curr.random.next — это копия узла, на который указывает random оригинала.
                curr.next.random = curr.random.next;
            }
            // Переходим к следующей паре (пропускаем только что обработанную копию)
            curr = curr.next.next;
        }
        
        // Шаг 3: Разделяем сплетенный список на оригинальный и скопированный
        curr = head;
        Node pseudoHead = new Node(0); // Псевдо-голова для сборки нового списка
        Node copyCurr = pseudoHead;
        
        while (curr != null) {
            Node nextNode = curr.next.next; // Запоминаем следующий оригинальный узел
            
            // Извлекаем копию
            Node copyNode = curr.next;
            copyCurr.next = copyNode;
            copyCurr = copyNode;
            
            // Восстанавливаем оригинальную связь
            curr.next = nextNode;
            
            curr = nextNode;
        }
        
        return pseudoHead.next;
    }
}

