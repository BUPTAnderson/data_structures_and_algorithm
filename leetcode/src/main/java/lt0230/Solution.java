package lt0230;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
  /**
   * 递归方式
   * @param root
   * @param k
   * @return
   */
  public int kthSmallest(TreeNode root, int k) {
    TreeNode target = new TreeNode(-1);
    findKthNode(root, k, target);
    return target.val;
  }

  private int findKthNode(TreeNode root, int k, TreeNode target) {
    if (root == null) return 0;
    int leftCount = findKthNode(root.left, k, target);
    if (leftCount == -1) { // -1 说明已经找到了，结束向上返回-1
      return leftCount;
    }
    if (leftCount + 1 == k) {
      target.val = root.val;
      return -1;
    }
    int rigthCount = findKthNode(root.right, k - leftCount -1, target); // 右子树找第(k - leftCount -1)大的节点
    return rigthCount == -1 ? -1 : (rigthCount + leftCount + 1);
  }


  /**
   * 非递归方式（推荐，避免Stack Overflow）
   * 实际是中序遍历非递归遍历
   * @param root
   * @param k
   * @return
   */
  public int kthSmallest2(TreeNode root, int k) {
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode current = root;

    while (current != null || !deque.isEmpty()) {
      while (current != null) {
        deque.push(current);
        current = current.left;
      }

      current = deque.pop();
      k--;
      if (k == 0) {
        return current.val;
      }

      current = current.right;
    }

    return -1;
  }
}
