package lt0092;

import common.ListNode;

/**
 * 该题要求是[left, right]这个范围内的链表反转，不是只反转left right这两个节点
 */
public class Solution {
  public ListNode reverseBetween(ListNode head, int left, int right) {
    if (left == 1) {
      ListNode previous = null;
      ListNode next = null;
      ListNode current = head;
      for (int i = 0; i < right; i++) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }
      head.next = current;
      return previous;
    } else {
      head.next = reverseBetween(head.next, left - 1, right - 1);
      return head;
    }
  }
}
