package lt0876;

import common.ListNode;

/**
 * Created by Anderson on 2021/12/25
 */
public class Solution {
  /**
   * Constraints:
   *
   * The number of nodes in the list is in the range [1, 100].
   * 1 <= Node.val <= 100
   *
   *
   * Input: head = [1,2,3,4,5]
   * Output: [3,4,5]
   *
   * Input: head = [1,2,3,4,5,6]
   * Output: [4,5,6]
   * @param head
   * @return
   */
  public ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
      fast = fast.next;
      if (fast == null) {
        break;
      }
    }
    return slow;
  }
}
