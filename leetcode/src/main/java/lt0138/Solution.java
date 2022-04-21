package lt0138;

/**
 *
 * Constraints:
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 *
 * 解题思路：
 * 1. 原始 A -> B -> C -> D
 * 每个节点拷贝一份，放在源节点后面
 * A -> a -> B -> b -> C -> c -> D -> d
 * 2. 如果A.random == null, a.random == null
 *    如果A.random ！= null 则a.random = A.random.next 因为每一个后面是拷贝，a.random= (A.random的下一个节点)
 * 3. 对这个新的链表做奇偶拆分即可
 * A -> B -> C -> D  a -> b -> c -> d
 */
public class Solution {
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    // 构建 A -> a -> B -> b -> C -> c -> D -> d
    Node current = head;
    Node tmp = null;
    while (current != null) {
      tmp = new Node(current.val);
      tmp.next = current.next;
      current.next = tmp;
      current = tmp.next;
    }
    // 2. 设置a -> b -> c -> d 的random
    current = head;
    while (current != null) {
      if (current.random != null) {
        current.next.random = current.random.next;
      }
      current = current.next.next;
    }
    // 3. 对A -> B -> C -> D  a -> b -> c -> d奇偶分离
    Node odd = head;
    Node even = head.next;
    Node evenHead = even;
    while (even.next != null) { // 肯定是偶数个节点，所以只判断even.next != null，不必判断even != null
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    odd.next = null; // odd.next还指向even，这里置为null

    return evenHead;
  }
}

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