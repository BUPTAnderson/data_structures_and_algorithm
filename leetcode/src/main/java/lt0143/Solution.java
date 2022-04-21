package lt0143;

import common.ListNode;

/**
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 * <p>
 * 思路1：
 * 先找到链表中间节点，将链表划分为前后两部分
 * 对后半部分逆序
 * 重新合并前后两部分
 * <p>
 * 思路2：
 * 递归方式
 * 参考：https://leetcode.com/problems/reorder-list/discuss/1640806/Java-or-2-Approach-or-2-Pointer-Approach
 */
public class Solution {
  public void reorderList(ListNode head) {
    // 同时把前后切分开
    // 这里我们找中间靠前一个的节点，这样的做的好处是，midNode.next就是后半部分链表，然后令midNode.next = null, 这样合并的时候前半部分一直合并到null条件就可以了
    // [1]返回1， [1,2]返回1， [1,2,3] 返回2 [1,2,3,4]返回2
    ListNode midNode = findMidNode(head);
    ListNode after = midNode.next;
    midNode.next = null;
    ListNode p2 = reverse(after);

    // merge
    ListNode p1 = head, tmp = null;
    while (p1 != null && p2 != null) {
      tmp = p1.next;
      p1.next = p2;
      p1 = p2;
      p2 = tmp;
    }
  }

  private ListNode findMidNode(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast.next != null) {
      fast = fast.next.next;
      if (fast == null) {
        break;
      }
      slow = slow.next;
    }
    return slow;
  }

  private ListNode reverse(ListNode head) {
    ListNode previoous = null, next = null;
    while (head != null) {
      next = head.next;
      head.next = previoous;
      previoous = head;
      head = next;
    }
    return previoous;
  }

  public void reorderListII(ListNode head) {
    ListNode[] left = new ListNode[1];// it will create in heap
    left[0] = head;
    reorder(left, head);

  }

  // left pointer will be created in heap and right pointer will be created in stack
  public void reorder(ListNode left[], ListNode right) {
    if (right == null) {
      return;
    }
    reorder(left, right.next);

    // in post area of recursion right pointer coming back(because of function remove from recursion stack)
    // and we move left pointer forward
    if (left[0].next != null) {
      ListNode leftNext = left[0].next;
      left[0].next = right;
      right.next = leftNext;
      left[0] = leftNext;
    }

    // as we need to  merge till left pointer behind the right pointer
    if (left[0].next == right) {
      left[0].next = null;
    }
  }
}
