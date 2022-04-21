package lt0445;

import common.ListNode;

/**
 * 思路：求出每个链表代表的值，然后构造链表
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int left = 0;
    while (l1 != null) {
      left = left * 10 + l1.val;
      l1 = l1.next;
    }
    int right = 0;
    while (l2 != null) {
      right = right * 10 + l2.val;
      l2 = l2.next;
    }

    int sum = left + right;
    ListNode result = new ListNode(sum % 10, null);
    sum = sum / 10;
    while (sum != 0) {
      ListNode ln = new ListNode(sum % 10, result);
      result = ln;
      sum = sum / 10;
    }
    return result;
  }

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(9, null);
    ListNode ln2 = new ListNode(9, ln1);
    ListNode ln3 = new ListNode(9, ln2);
    ListNode ln4 = new ListNode(9, ln3);
    ListNode ln5 = new ListNode(9, ln4);
    ListNode ln6 = new ListNode(9, ln5);
    ListNode ln7 = new ListNode(9, ln6);
    ListNode ln8 = new ListNode(9, ln7);
    ListNode ln9 = new ListNode(9, ln8);
    ListNode ln10 = new ListNode(3, ln9);

    ListNode ln11 = new ListNode(7, null);
    Solution solution = new Solution();
    ListNode result = solution.addTwoNumbers(ln10, ln11);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }
}
