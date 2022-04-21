package lt0101;

import common.TreeNode;

import java.util.LinkedList;

public class Solution {
  /**
   * // 递归方式
   * @param root
   * @return
   */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isSameTree(root.left, root.right);
  }

  private boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null || q == null) {
      return p == q;
    }
    if (p.val == q.val) { // 注意这里是镜像，所以是p.left 与 q.right对比， p.right与q.left对比
      return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
    return false;
  }

  /**
   * 循环方式, 队列
   * @param root
   * @return
   */
  public boolean isSymmetric2(TreeNode root) {
    if (root == null) {
      return true;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addFirst(root.left);
    queue.addLast(root.right);

    while (!queue.isEmpty()) {
      TreeNode left = queue.removeFirst();
      TreeNode right = queue.removeLast();
      if (left == null || right == null) {
        if (left != right) {
          return false;
        }
      } else if (left.val != right.val) {
        return false;
      } else {
        queue.addFirst(left.right);
        queue.addFirst(left.left);
        queue.addLast(right.left);
        queue.addLast(right.right);
      }
    }
    return true;
  }

  /**
   * 循环方式, 队列，只操作一边
   * @param root
   * @return
   */
  public boolean isSymmetric3(TreeNode root) {
    if (root == null) {
      return true;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.addFirst(root.left);
    queue.addLast(root.right);

    while (!queue.isEmpty()) {
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if (left == null || right == null) {
        if (left != right) {
          return false;
        }
      } else if (left.val != right.val) {
        return false;
      } else {
        queue.add(left.left);
        queue.add(right.right);
        queue.add(left.right);
        queue.add(right.left);
      }
    }
    return true;
  }
}
