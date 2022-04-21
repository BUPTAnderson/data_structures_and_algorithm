package lt0105;

import common.TreeNode;

public class Solution {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return constructTree(preorder, inorder, 0, 0, preorder.length);
  }

  private TreeNode constructTree(int[] preorder, int[] inorder, int rootIndex, int start, int end) {
    if (start >= end) return null;
    TreeNode root = new TreeNode(preorder[rootIndex]);
    int index = -1;
    for (int i = start; i < end; i++) {
      if (inorder[i] == preorder[rootIndex]) {
        index = i;
        break;
      }
    }
    root.left = constructTree(preorder, inorder, rootIndex + 1, start, index);
    root.right = constructTree(preorder, inorder, rootIndex + index - start + 1, index + 1, end);

    return root;
  }

  public static void main(String[] args) {
    // [1,2,3]
    // [2,3,1]
    // [1,2,null,3]
    // [1,2,null,null,3]
    Solution solution = new Solution();
    TreeNode treeNode = solution.buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    System.out.println(treeNode.val);
  }
}
