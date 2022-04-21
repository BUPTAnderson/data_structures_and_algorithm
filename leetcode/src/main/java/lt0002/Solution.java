package lt0002;

import common.ListNode;

public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode result = new ListNode(-1, null);
    ListNode current = result;
    int carry = 0;
    int sum = 0;
    while (l1 != null && l2 != null) {
      sum = l1.val + l2.val + carry;
      carry = sum >= 10 ? 1 : 0;
      current.next = new ListNode(sum % 10, null);
      current = current.next;
      l1 = l1.next;
      l2 = l2.next;
    }
    while (l1 != null) {
      sum = l1.val + carry;
      carry = sum >= 10 ? 1 : 0;
      current.next = new ListNode(sum % 10, null);
      current = current.next;
      l1 = l1.next;
    }
    while (l2 != null) {
      sum = l2.val + carry;
      carry = sum >= 10 ? 1 : 0;
      current.next = new ListNode(sum % 10, null);
      current = current.next;
      l2 = l2.next;
    }
    if (carry != 0) {
      current.next = new ListNode(carry, null);
    }

    return result.next;
  }
}
