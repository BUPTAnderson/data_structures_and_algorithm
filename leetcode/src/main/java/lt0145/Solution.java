package lt0145;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  /**
   * 思路 [3, 1, 2]
   *   3
   *  / \
   * 1   2
   * 先序遍历 3 1 2,
   * 先序遍历按照 根->右子树->左子树 3 2 1 （这种遍历成为逆后续遍历）
   * 为什么是逆后续遍历呢？我们看下后续遍历是：1 2 3, 正好与上面的(3 2 1)顺序是相反的。
   * 所以我们可以修改下先序遍历的代码按照 3 2 1 进行输出，让后将输出通过addFirst的方式放到LinkedList里面即可
   *
   * @param root
   * @return
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> list = new LinkedList<>();
    if (root == null) {
      return list;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.push(root);
    TreeNode current = null;
    while (!deque.isEmpty()) {
      current = deque.pop();
      list.addFirst(current.val);
      if (current.left != null) {
        deque.push(current.left);
      }
      if (current.right != null) {
        deque.push(current.right);
      }
    }

    return list;
  }
}
