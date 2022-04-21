package lt0147;

import common.ListNode;

public class Solution {
  public ListNode insertionSortList(ListNode head) {
    ListNode result = new ListNode(0, null);
    ListNode pre = result, current = head, next = null;
    while (current != null) {
      next = current.next;
      while (pre.next != null && pre.next.val < current.val) {
        pre = pre.next;
      }
      current.next = pre.next;
      pre.next = current;
      current = next;
      pre = result;
    }

    return result.next;
  }


  public static void main(String[] args) {
    ListNode ln1 = new ListNode(3, null);
    ListNode ln2 = new ListNode(1, ln1);
    ListNode ln3 = new ListNode(2, ln2);
    ListNode ln4 = new ListNode(4, ln3);
    Solution solution = new Solution();
    ListNode result = solution.insertionSortList(ln4);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }
}
