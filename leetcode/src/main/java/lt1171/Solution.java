package lt1171;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里是要求连续的几个节点加起来为0，则移除。
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class Solution {
  public ListNode removeZeroSumSublists(ListNode head) {
    Map<Integer, ListNode> sumToFarthestNodeMap = new HashMap<>();

    // Need the dummy node to track the new head if changed.
    ListNode preHead = new ListNode(0);
    preHead.next = head;

    // First iteration to compute the map.
    int sum = 0;
    for (ListNode p = preHead; p != null; p = p.next) {
      sum += p.val;
      sumToFarthestNodeMap.put(sum, p);
    }

    // Second iteration to re-connect the nodes to the farthest node where the sum stays unchanged
    sum = 0;
    for (ListNode p = preHead; p != null; p = p.next) {
      sum += p.val;
      p.next = sumToFarthestNodeMap.get(sum).next;
    }

    // Done, return the head from preHead
    return preHead.next;
  }

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(0, null);
    ListNode ln2 = new ListNode(4, ln1);
    ListNode ln3 = new ListNode(-3, ln2);
    ListNode ln4 = new ListNode(2, ln3);
    ListNode ln5 = new ListNode(1, ln4);

    Solution solution = new Solution();
    ListNode resurlt = solution.removeZeroSumSublists(ln5);
    while (resurlt != null) {
      System.out.println(resurlt.val);
      resurlt = resurlt.next;
    }
  }
}
