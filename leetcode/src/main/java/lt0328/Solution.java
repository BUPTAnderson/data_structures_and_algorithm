package lt0328;

import common.ListNode;

/**
 * n == number of nodes in the linked list
 * 0 <= n <= 104
 * -106 <= Node.val <= 106
 *
 * 方法II更简洁
 */
public class Solution {
  // oms
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode odd = head;
    ListNode oc = odd;
    ListNode even = head.next;
    ListNode ec = even;
    head = head.next.next;
    while (head != null) {
      oc.next = head;
      oc = head;
      head = head.next;
      if (head != null) {
        ec.next = head;
        ec = head;
        head = head.next;
      } else {
        ec.next = null;
      }
    }
    oc.next = even;
    return odd;
  }

  // 1ms
  public ListNode oddEvenListII(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode odd = head, even = head.next, evenHead = even;
    while (even != null && even.next != null) { // 关键一行
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    odd.next = evenHead;
    return head;
  }

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(5, null);
    ListNode ln2 = new ListNode(4, ln1);
    ListNode ln3 = new ListNode(3, ln2);
    ListNode ln4 = new ListNode(2, ln3);
    ListNode ln5 = new ListNode(1, ln4);
    Solution solution = new Solution();
    ListNode result = solution.oddEvenListII(ln5);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }
}
