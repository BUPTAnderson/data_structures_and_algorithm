package lt0148;

import common.ListNode;

/**
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 */
public class Solution {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // 这里找中间靠前的点，[1, 2] -> 1; [1,2,3] -> 1; [1,2,3,4] -> 2
    ListNode slow = head, fast = head.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode rear = slow.next;
    slow.next = null;
    ListNode front = sortList(head);
    ListNode behind = sortList(rear);
    // 归并排序, 两个有序链表合并
    ListNode result = new ListNode(0);
    ListNode current = result;
    while (front != null && behind != null) {
      if (front.val < behind.val) {
        current.next = front;
        front = front.next;
      } else {
        current.next = behind;
        behind = behind.next;
      }
      current = current.next;
    }
    if (front != null) {
      current.next = front;
    }
    if (behind != null) {
      current.next = behind;
    }
    return result.next;
  }
}
