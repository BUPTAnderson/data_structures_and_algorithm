package lt0111;

import common.TreeNode;

public class Solution {
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    } else if (root.left == null) {
      return minDepth(root.right) + 1;
    } else if (root.right == null) {
      return minDepth(root.left) + 1;
    } else {
      int left = minDepth(root.left);
      int right = minDepth(root.right);

      return left > right ? right + 1 : left + 1;
    }
  }

  public int minDepth2(TreeNode root) {
    if(root == null) return 0;
    int left = minDepth2(root.left);
    int right = minDepth2(root.right);
    // 这里处理比较巧妙，如果left或right为0说明左或右存在空，left + right + 1， 否则左右都不为空，选小的那个加1即可
    return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

  }
}
