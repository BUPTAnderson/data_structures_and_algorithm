package lt0061;

import common.ListNode;

/**
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * 思路：
 * 1. 求链表长度length
 * 2. l = k % length
 * 3. 将l后面的部分移动到链表前面
 */
public class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode current = head;
    int length = 1;
    while (current.next != null) {
      length++;
      current = current.next;
    }
    // 此时current是最后一个节点
    int index = length - (k % length);
    if (index == length) {
      return head;
    }
    // 尾部指向head,链接起来
    current.next = head;
    current = head;
    while (index > 1) {
      current = current.next;
      index--;
    }
    ListNode result = current.next;
    current.next = null;
    return result;
  }

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(5, null);
    ListNode ln2 = new ListNode(4, ln1);
    ListNode ln3 = new ListNode(3, ln2);
    ListNode ln4 = new ListNode(2, ln3);
    ListNode ln5 = new ListNode(1, ln4);

    Solution solution = new Solution();
    ListNode result = solution.rotateRight(ln5, 2);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }
}
