package lt0024;

import common.ListNode;

public class Solution {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    int tmp = head.val;
    head.val = head.next.val;
    head.next.val = tmp;
    head.next.next = swapPairs(head.next.next);
    return head;
  }

  // 递归
  public ListNode swapPairsII(ListNode head) {
    if ((head == null)||(head.next == null))
      return head;
    ListNode n = head.next;
    head.next = swapPairs(head.next.next);
    n.next = head;
    return n;
  }
}
