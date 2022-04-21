package lt0236;

import common.TreeNode;

public class Solution {

  /**
   * 这个方法麻烦些，可以看第二种方法
   * @param root
   * @param p
   * @param q
   * @return
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return find(root, p, q, new Find());
  }

  private TreeNode find(TreeNode root, TreeNode p, TreeNode q, Find find) {
    if (root == null) return null;

    TreeNode result =  find(root.left, p, q, find);
    if (result != null) return result;

    if (root.val == p.val) find.findP = true;
    if (root.val == q.val) find.findQ = true;
    if (find.findP && find.findQ) return root;
    boolean tmp = find.findP || find.findQ;
    result = find(root.right, p, q, find);
    if (find.findP && find.findQ) {
      return tmp ? root : result;// tmp=true: 左子树或root是其中一个节点,右子树找到另一个，返回tmp。tmp=false，右子树找到了两个节点，返回右子树返回的result
    } else {
      return null;
    }
  }

  /**
   * 推荐方法
   * @param root
   * @param p
   * @param q
   * @return
   */
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q ) return root;
    TreeNode left = lowestCommonAncestor2(root.left, p, q);
    TreeNode right = lowestCommonAncestor2(root.right, p, q);
    if (left != null && right != null) return root;
    return  left == null ? right : left;
  }

  static class Find {
    private boolean findP = false;
    private boolean findQ = false;
  }
}
