package lt0025;

import common.ListNode;

/**
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 */
public class Solution {
  // 如果剩下的元素不足k个，则剩下的元素不反转，直接返回，所以每次处理前先找k个元素，找到了，对k个元素反转，没找到，直接返回。
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode count = head;
    for (int i = 0; i < k; i++) {
      if (count == null) {
        return head;
      }
      count = count.next;
    }
    ListNode current = head;
    ListNode previous = head;
    ListNode next = head;
    for (int i = 0; i < k; i++) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    head.next = reverseKGroup(next, k);
    return previous;
  }

  // 剩下的k个元素也反转的情形
  public ListNode reverseKGroupII(ListNode head, int k) {
    ListNode current = head;
    ListNode previous = head;
    ListNode next = head;
    int count = 0;
    while (count < k && current != null)
    for (int i = 0; i < k; i++) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
      count++;
    }
    if (next != null) {
      head.next = reverseKGroup(next, k);
    }
    return previous;
  }
}
