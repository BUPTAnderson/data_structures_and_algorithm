package lt1019;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 */
class Solution {
  public int[] nextLargerNodes(ListNode head) {
    List<Integer> list = new ArrayList<>();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }

    Deque<Integer> stack = new LinkedList<>();
//    Deque<Integer> stack = new ArrayDeque<>();
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      while (!stack.isEmpty() && (list.get(stack.peek()) < list.get(i))) {
        result[stack.pop()] = list.get(i);
      }
      stack.push(i);
    }
    return result;
  }
}
