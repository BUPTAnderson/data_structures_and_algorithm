package lt0112;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;

    if (root.left == null && root.right == null) {
      return root.val == targetSum;
    } else {
//      boolean left = hasPathSum(root.left, targetSum - root.val);
//      boolean right = hasPathSum(root.right, targetSum - root.val);
//      return left || right;
      return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
  }
}
