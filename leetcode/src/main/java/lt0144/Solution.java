package lt0144;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
      return list;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.push(root);
    TreeNode current = null;
    while (!deque.isEmpty()) {
      current = deque.pop();
      list.add(current.val);
      if (current.right != null) {
        deque.push(current.right);
      }
      if (current.left != null) {
        deque.push(current.left);
      }
    }
    return list;
  }
}

