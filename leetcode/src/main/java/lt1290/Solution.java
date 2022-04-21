package lt1290;

import common.ListNode;

/**
 * Constraints:
 *
 * The Linked List is not empty.
 * Number of nodes will not exceed 30.
 * Each node's value is either 0 or 1.
 */
public class Solution {
  public int getDecimalValue(ListNode head) {
    int sum = 0;
    while (head != null) {
      sum = sum * 2 + head.val;
      head = head.next;
    }
    return sum;
  }


  public int getDecimalValueII(ListNode head) {
    while (head.next != null) {
      head.next.val = head.val * 2 + head.next.val;
      head = head.next;
    }
    return head.val;
  }
}
