package lt0083;

import common.ListNode;

public class Solution {
  // 非递归
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode current = head;
    while (current.next != null) {
      if (current.val == current.next.val) {
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }

    return head;
  }

  // 递归
  public ListNode deleteDuplicatesII(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode result = deleteDuplicates(head.next);
    if (head.val == result.val) {
      head.next = result.next;
    }
    return head;
  }

  // 递归
  public ListNode deleteDuplicatesIII(ListNode head) {
    if (head == null || head.next == null) return head;
    head.next = deleteDuplicates(head.next);
    return head.val == head.next.val ? head.next : head;
  }
}
