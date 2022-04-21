package lt0094;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  /**
   * 中序遍历
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode current = root;
    while (current != null || !deque.isEmpty()) {
      while (current != null) {
        deque.push(current);
        current = current.left;
      }
      current = deque.pop();
      list.add(current.val);
      current = current.right;
    }

    return list;
  }

  /**
   * 先序遍历
   * 和上面一样，只需要调整下list.add(current.val);的位置即可
   * @param root
   * @return
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode current = root;
    while (current != null || !deque.isEmpty()) {
      while (current != null) {
        deque.push(current);
        list.add(current.val);
        current = current.left;
      }
      current = deque.pop();
      current = current.right;
    }

    return list;
  }

  /**
   * 后续遍历
   * 和中序遍历一样，只需将list改为LinkedList，同时将right和left的访问顺序交换即可(具体解释可以看lt0145)
   * @param root
   * @return
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Integer> list = new LinkedList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode current = root;
    while (current != null || !deque.isEmpty()) {
      while (current != null) {
        deque.push(current);
        list.addFirst(current.val);
        current = current.right;
      }
      current = deque.pop();
      current = current.left;
    }

    return list;
  }
}
