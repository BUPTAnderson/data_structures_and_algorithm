package lt0019;

import common.ListNode;

/**
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * Created by Anderson on 2021/12/25
 */
public class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
    ListNode slow = head;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    if (fast == null) {
      return head.next;
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return head;
  }

  public ListNode findNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
    ListNode slow = head;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    while (fast != null) { // 这里和上面比是多走了一步，所以slow直接就等于倒数第n个节点
      fast = fast.next;
      slow = slow.next;
    }

    return slow;
  }

  public static void main(String[] args) {

  }
}
