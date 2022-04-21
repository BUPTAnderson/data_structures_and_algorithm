package lt0023;

import common.ListNode;

import java.util.Arrays;

public class Solution {
  // 当前merge分开的方式，时间是3ms。两个方法合起来在SolutionII， 时间为1ms
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    } else if (lists.length == 1) {
      return lists[0];
    } else if (lists.length == 2) {
      return mergeTwoLists(lists[0], lists[1]);
    } else {
      int mid = lists.length / 2;
      return mergeTwoLists(mergeKLists(Arrays.copyOfRange(lists,0, mid)),
          mergeKLists(Arrays.copyOfRange(lists,mid, lists.length)));
    }
  }

  public ListNode mergeTwoLists(ListNode left, ListNode right) {
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    ListNode create = new ListNode(-1, null);
    ListNode current = create;
    while (left != null && right != null) {
      if (left.val < right.val) {
        current.next = left;
        left = left.next;
      } else {
        current.next = right;
        right = right.next;
      }
      current = current.next;
    }
    if (left != null) {
      current.next = left;
    }
    if (right != null) {
      current.next = right;
    }

    return create.next;
  }
}
