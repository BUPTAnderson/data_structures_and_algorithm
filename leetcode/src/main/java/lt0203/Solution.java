package lt0203;

import common.ListNode;

/**
 * Created by Anderson on 2021/12/25
 */
class Solution {
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return null;
    }
    ListNode pre = new ListNode(-1, head); // 加了一个哨兵来处理头节点就是要删除的节点
    ListNode current = pre;
    while (current.next != null) {
      if (current.next.val != val) {
        current = current.next;
      } else {
        current.next = current.next.next;
      }
    }
    return pre.next;
  }

  /**
   * 递归方式
   */
  public ListNode removeElementsII(ListNode head, int val) {
    if (head == null) return null;
    head.next = removeElements(head.next, val);
    return head.val == val ? head.next : head;
  }
}
