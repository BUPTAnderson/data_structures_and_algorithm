package lt0082;

import common.ListNode;

public class Solution {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode pre = new ListNode(-200, head);
    ListNode rear = pre;
    ListNode middle = head;
    ListNode front = head.next;
    while (front != null) {
      if (front.val == middle.val) { // 相等就只移动front
        front = front.next;
      } else { // 不想等，就判断中间是否有跳过节点
        if (middle.next != front) { // 说明middle也是刚才跳过的重复节点，重新设置middle和rear
          middle = front;
          rear.next = middle;
        } else { // 没有跳过，说明没出现重复节点，大家一起向后移动
          middle = middle.next;
          rear = rear.next;
        }
        front = front.next;
      }
    }

    // 链表最后几个节点都是重复的，这时候要单独处理下
    if (middle.next != front) {
      middle = front;
      rear.next = middle;
    }

    return pre.next;
  }

  // 递归方式
  public ListNode deleteDuplicatesII(ListNode head) {
    if (head == null) return null;

    if (head.next != null && head.val == head.next.val) {
      while (head.next != null && head.val == head.next.val) {
        head = head.next;
      }
      return deleteDuplicates(head.next);
    } else {
      head.next = deleteDuplicates(head.next);
    }
    return head;
  }
}
