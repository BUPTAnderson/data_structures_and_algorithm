package common;

/**
 * 这里只是为了不重复定义，为了方便引用，这里属性都设置为public的
 */
public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
