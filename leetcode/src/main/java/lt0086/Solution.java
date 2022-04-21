package lt0086;

import common.ListNode;

/**
 * 小于x的排到x前面，其他都不变，且去掉x后各个节点前后位置不变
 * 比如：
 * Input：
 * [1,4,8,3,2,5,2,6,7,7,9]
 * 7
 * Output：[1,4,3,2,5,2,6,8,7,7,9]
 *
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Solution {
  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode little = new ListNode(-1, null);
    ListNode littleHead = little;
    ListNode bigger = new ListNode(-1, null);
    ListNode biggerHead = bigger;
    while (head != null) {
      if (head.val < x) {
        little.next = head;
        little = head;
      } else {
        bigger.next = head;
        bigger = head;
      }
      head = head.next;
    }
    bigger.next = null;
    little.next = biggerHead.next;
    return littleHead.next;
  }
}
