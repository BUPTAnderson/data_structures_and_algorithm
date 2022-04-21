package lt0559;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anderson on 2022/3/3
 */
class Node {
  public int val;
  public List<Node> children;

  public Node() {}

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}

public class Solution {
  public int maxDepth(Node root) {
    if (root == null) {
      return 0;
    }
    Deque<Node> queue = new LinkedList<>();
    queue.add(root);
    int depth = 0;
    Node current = null;
    int size = 0;
    while (!queue.isEmpty()) {
      depth++;
      size = queue.size();
      for (int i = 0; i < size; i++) {
        current = queue.poll();
        for (Node child : current.children) {
          queue.add(child);
        }
      }
    }
    return depth;
  }

  public int maxDepth2(Node root) {
    if (root == null) {
      return 0;
    }
    if (root.children != null) {
      return root.children.stream().mapToInt(this::maxDepth2).max().getAsInt();
    } else {
      return 1;
    }
  }

  public static void main(String[] args) {
    Deque<Integer> queue = new LinkedList<>();
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    queue.offer(4);
    queue.offer(5);
    System.out.println(queue.poll());
    System.out.println(queue.poll());
  }
}
