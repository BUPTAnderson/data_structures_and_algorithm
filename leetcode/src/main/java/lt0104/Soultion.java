package lt0104;

import common.TreeNode;

/**
 * Created by Anderson on 2022/3/3
 */
public class Soultion {
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    } else {
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
  }
}
