package lt0023;

import common.ListNode;

import java.util.Arrays;

public class SolutionII {
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode left = null;
    ListNode right = null;
    if (lists.length == 0) {
      return null;
    } else if (lists.length == 1) {
      return lists[0];
    } else if (lists.length == 2) {
      left = lists[0];
      right = lists[1];
    } else {
      int mid = lists.length / 2;
      ListNode[] ll = new ListNode[mid];
      int i = 0;
      while (i < mid) {
        ll[i] = lists[i];
        i++;
      }
      ListNode[] rl = new ListNode[lists.length - mid];
      while (i < lists.length) {
        rl[i - mid] = lists[i];
        i++;
      }

      left = mergeKLists(ll);
      right = mergeKLists(rl);
    }
    // two list merge
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

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(5, null);
    ListNode ln2 = new ListNode(4, ln1);
    ListNode ln3 = new ListNode(1, ln2);

    ListNode ln4 = new ListNode(4, null);
    ListNode ln5 = new ListNode(3, ln4);
    ListNode ln6 = new ListNode(1, ln5);

    ListNode ln7 = new ListNode(6, null);
    ListNode ln8 = new ListNode(2, ln7);

    ListNode[] ln = new ListNode[]{ln3, ln6, ln8};
    SolutionII solutionII = new SolutionII();
    ListNode result = solutionII.mergeKLists(ln);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }
}
