package lt0234;

import common.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    // 快指针fast， 慢指针还是用head
    ListNode fast = head;

    ListNode previous = null;
    ListNode next = null;
    do {
      // 移动快指针
      fast = fast.next.next;

      // 反转前半部分节点。
      // 也可以先找到中间节点然后，反转后半部分节点，但是这个过程中在找中间节点的过程中实际遍历了前半部分节点。不如在找中间节点的时候直接把前半部分反转。
      // 这个好处是先找中间节点，然后中间节点后面的部分，然后比较，反转前半部分节点的方法将中中间节点和反转的逻辑糅合在一起了，难度大点。
      next = head.next;
      head.next = previous;
      previous = head;
      head = next;

    } while (fast != null && fast.next != null);


    // if (fast == null) // 偶数个节点，两边各一半， pre是前面一半反转后的头指针， next是后一半的头指针
    // if (fat.next == null) 基数个节点，next为中间节点
    if (fast != null) {
      next = next.next;
    }
    while (previous != null) {
      if (previous.val == next.val) {
        previous = previous.next;
        next = next.next;
      } else {
        return false;
      }
    }
    return true;
  }
}
