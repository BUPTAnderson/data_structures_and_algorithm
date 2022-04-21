package lt0235;

import common.TreeNode;

public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < q.val) {
      if (root.val >= p.val && root.val <= q.val) {
        return root;
      } else if (root.val > q.val) {
        return lowestCommonAncestor(root.left, p, q);
      } else {
        return lowestCommonAncestor(root.right, p, q);
      }
    } else {
      if (root.val >= q.val && root.val <= p.val) {
        return root;
      } else if (root.val > p.val) {
        return lowestCommonAncestor(root.left, p, q);
      } else {
        return lowestCommonAncestor(root.right, p, q);
      }
    }
  }

  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    if(root.val > p.val && root.val > q.val){
      return lowestCommonAncestor(root.left, p, q);
    }else if(root.val < p.val && root.val < q.val){
      return lowestCommonAncestor(root.right, p, q);
    }else{
      return root;
    }
  }

  public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
    while (true) {
      if (root.val > p.val && root.val > q.val) {
        root = root.left;
      } else if (root.val < p.val && root.val < q.val) {
        root = root.right;
      } else {
        return root;
      }
    }
  }
}
