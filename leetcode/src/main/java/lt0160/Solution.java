package lt0160;

import common.ListNode;

/**
 * Constraints:
 *
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 */
public class Solution {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenghtA = 0, lenghtB = 0;
    ListNode a = headA, b = headB;
    while (a != null) {
      a = a.next;
      lenghtA++;
    }
    while (b != null) {
      b = b.next;
      lenghtB++;
    }
    a = headA;
    b = headB;
    int step = 0;
    if (lenghtA > lenghtB) {
      step = lenghtA - lenghtB;
      while (step > 0) {
        a = a.next;
        step--;
      }
    } else {
      step = lenghtB - lenghtA;
      while (step > 0) {
        b= b.next;
        step--;
      }
    }

    while (a != b) {
      a = a.next;
      b = b.next;
    }

    return a;
  }

  public ListNode getIntersectionNodeII(ListNode headA, ListNode headB) {
    ListNode a = headA, b = headB;
    while (a != b) {
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }
    return a;
  }
}
