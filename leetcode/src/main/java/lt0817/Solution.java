package lt0817;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1：采用map来记录数组中有哪些数字。
 * 2：遍历链表，若链表中的数字在map中出现，则说明存在，是一个组件中的元素.
 * 3：直到链表中的数字在map中没有的时候，表示一个组件结束。计数器 + 1.
 * 4：按照上述逻辑，遍历链表到结束。
 */
public class Solution {
  public int numComponents(ListNode head, int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i : nums) {
      set.add(i);
    }
    int count = 0;
    ListNode current = head;
    while (current != null) {
      if (set.contains(current.val)) {
        while (current.next != null && set.contains(current.next.val)) {
          current = current.next;
        }
        count++;
      }
      current = current.next;
    }
    return count;
  }
}
