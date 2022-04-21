package lt0021;

import common.ListNode;

/**
 * 非的递归方式快
 * 递归方式慢
 */
public class Solution {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode head = new ListNode();
    ListNode current = head;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }
    if (l1 != null) {
      current.next = l1;
    }
    if (l2 != null) {
      current.next = l2;
    }

    return head.next;
  }

  /**
   * 递归方式
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val < l2.val) {
      l1.next = mergeTwoListsII(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoListsII(l2.next, l1);
      return l2;
    }


  }
}
