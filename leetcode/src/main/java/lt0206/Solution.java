package lt0206;

import common.ListNode;

public class Solution {
  /**
   * 非递归
   * @param head
   * @return
   */
  public ListNode reverseListI(ListNode head) {
    ListNode previousNode = null;
    ListNode nextNode = null;

    while (head != null) {
      nextNode = head.next;
      head.next = previousNode;
      previousNode = head;
      head = nextNode;
    }

    return previousNode;
  }

  /**
   * 递归
   * @param head
   * @return
   */
  public ListNode reverseListII(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode nextNode = head.next;
    head.next = null;
    ListNode reverseNode = reverseListI(nextNode);
    nextNode.next = head;
    return reverseNode;
  }
}
