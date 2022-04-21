package lt1367;

import common.ListNode;
import common.TreeNode;

/**
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [1, 2500].
 * The number of nodes in the list will be in the range [1, 100].
 * 1 <= Node.val <= 100 for each node in the linked list and binary tree.
 */
public class Solution {
  public boolean isSubPath(ListNode head, TreeNode root) {
    if (head == null) { // 注意要先判断head == null再判断root == null
      return true;
    }
    if (root == null) {
      return false;
    }
    return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
  }

  private boolean dfs(ListNode head, TreeNode root) {
    if (head == null) {
      return true;
    }
    if (root == null) {
      return false;
    }
    return (head.val == root.val) && (dfs(head.next, root.left) || dfs(head.next, root.right));
  }
}
