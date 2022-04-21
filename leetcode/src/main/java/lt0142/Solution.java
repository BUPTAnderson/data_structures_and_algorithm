package lt0142;

import common.ListNode;

/**
 * 1. 先判断是否是环，快慢指针
 * 2. 是环，当slow=fast时，将slow重新指向head，然后slow和fast同时一步一步的向后走，当slow和fast再次相等时即为环的起始位置。
 * 解释：
 *    假设环的长度是 m, 进入环前经历的node的个数是 k , 那么，假设经过了时间 t，那么速度为2 的指针距离起始点的位置是:  k + (2t - k) % m = k + (2t - k) - xm . 同理，速度为1的指针距离起始点的位置是 k + (t - k) % m = k + (t - k) - ym。
 *
 *     如果 k + (2t - k) - xm =  k  + (t - k) - ym ，可以得到 t = m (x - y)。 那么当 t 最小为m的时候，也就是说，两个指针相聚在距离环起始点 m - k 的环内。
 * 换句话说，如果把一个指针移到链表的头部，然后两个指针都以 1 的速度前进，那么它们经过 k 时间后，就可以在环的起始点相遇。
 *
 * 原文链接：https://blog.csdn.net/beiyetengqing/article/details/7603997
 */
public class Solution {
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }

    // 起始slow = fast = head, 为了后面号比较，这里先让slow和fast按照自己的规则执行一次
    ListNode slow = head.next;
    ListNode fast = head.next.next;

    while (fast != null) { // 只需判断fast即可
      if (slow == fast) {
        slow = head;
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;
      }
      slow = slow.next;
      fast = fast.next;
      if (fast != null) {
        fast = fast.next;
      }
    }
    return null;
  }
}
