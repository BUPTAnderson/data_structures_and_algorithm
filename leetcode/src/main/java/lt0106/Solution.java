package lt0106;

import common.TreeNode;

public class Solution {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    return constructTree(postorder, inorder, postorder.length - 1, 0, postorder.length);
  }

  private TreeNode constructTree(int[] postorder, int[] inorder, int rootIndex, int start, int end) {
    if (start >= end) return null;
    TreeNode root = new TreeNode(postorder[rootIndex]);
    int index = -1;
    for (int i = start; i < end; i++) {
      if (inorder[i] == postorder[rootIndex]) {
        index = i;
        break;
      }
    }
    root.left = constructTree(postorder, inorder, rootIndex + index - end, start, index);
    root.right = constructTree(postorder, inorder, rootIndex - 1, index + 1, end);

    return root;
  }
}
