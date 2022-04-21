package edu.bupt.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
  private Node tree;

  // 非递归方式查找
  public Node find(int data) {
    Node p = tree;
    while (p != null) {
      if (data < p.data) p = p.left;
      else if (data > p.data) p = p.right;
      else return p;
    }
    return null;
  }

  // 递归方式查找
  public Node findecursion(int data) {
    return find(tree, data);
  }

  private Node find(Node p, int data) {
    if (p == null) {
      return null;
    }
    if (p.data > data) {
      return find(p.left, data);
    } else if (p.data < data) {
      return find(p.right, data);
    } else {
      return p;
    }
  }

  // 非递归插入
  public void insert(int data) {
    if (tree == null) {
      tree = new Node(data);
      return;
    }

    Node p = tree;
    while (p != null) {
      if (data > p.data) {
        if (p.right == null) {
          p.right = new Node(data);
          return;
        }
        p = p.right;
      } else { // data < p.data
        if (p.left == null) {
          p.left = new Node(data);
          return;
        }
        p = p.left;
      }
    }
  }

  // 递归插入
  public void insetRecursion(int data) {
    if (tree == null) {
      tree = new Node(data);
      return;
    }
    insetRecursion(tree, data);
  }

  private void insetRecursion(Node p, int data) {
    if (p.data > data) {
      if (p.left == null) {
        p.left = new Node(data);
        return;
      }
      insetRecursion(p.left, data);
    } else if (p.data < data) {
      if (p.right == null) {
        p.right = new Node(data);
        return;
      }
      insetRecursion(p.right, data);
    } else {
      // no-op
    }
  }

  // BST中查找前驱节点
  // 前驱节点指的是小于该键的最大键(中序遍历前驱节点)
  // 两种情况：1.该节点存在左子树，则前驱是左子树最大值(比如示例中41的前驱节点39)
  // 2. 没有左子树但是该节点位于查找链路上某个节点的右子树中，则所在最小右子树的父节点是前驱节点(比如示例中31的前驱节点29)
  public Node findPredecessor(int data) {
    if (tree == null) {
      return null;
    }

    Node pre = null;
    Node current = tree;
    while (current != null) {
      if (current.data > data) {
        current = current.left;
      } else if (current.data < data) {
        pre = current;
        current = current.right;
      } else {
        break;
      }
    }

    if (current == null) {
      return null;
    }

    if (current.left != null) { // 左孩子不为null，则前驱节点为左子树中的最大值
      Node tmp = current.left;
      while (tmp.right != null) { // 循环查找为叶子节点的右孩子即为最大值
        tmp = tmp.right;
      }
      return tmp;
    }

    return pre; // 没有左孩子，则pre即为前驱
  }

  // 查找后继节点
  // 后继节点指的是大于该键的最小键(中序遍历后继节点)
  // 两种情况：1.该节点存在右子树，则后继是右子树最小值 (比如示例中41的后继节点是53)
  // 2. 没有右子树但是该节点位于查找链路上某个节点的左子树中，则所在最小左子树的父节点是后继节点(比如示例中39的后继节点是41)
  public Node findSuccessor(int data) {
    if (tree == null) {
      return null;
    }

    Node pre = null;
    Node current = tree;
    while (current != null) {
      if (current.data > data) {
        pre = current;
        current = current.left;
      } else if (current.data < data) {
        current = current.right;
      } else {
        break;
      }
    }

    if (current == null) {
      return null;
    }

    if (current.right != null) { // 右孩子不为null，则后继节点为右子树中的最小值
      Node tmp = current.right;
      while (tmp.left != null) { // 循环查找为叶子节点的左孩子即为最小值
        tmp = tmp.left;
      }
      return tmp;
    }

    return pre; // 没有左孩子，则pre即为后继
  }

  public void delete(int data) {
    Node p = tree; // p指向要删除的节点，初始化指向根节点
    Node pp = null; // pp记录的是p的父节点
    while (p != null && p.data != data) {
      pp = p;
      if (data > p.data) p = p.right;
      else p = p.left;
    }
    if (p == null) return; // 没有找到

    // 要删除的节点有两个子节点
    if (p.left != null && p.right != null) { // 查找右子树中最小节点
      Node minP = p.right;
      Node minPP = p; // minPP表示minP的父节点
      while (minP.left != null) {
        minPP = minP;
        minP = minP.left;
      }
      p.data = minP.data; // 将minP的数据替换到p中
      p = minP; // 下面就变成了删除minP了
      pp = minPP;
    }

    // 删除节点是叶子节点或者仅有一个子节点
    Node child; // p的子节点
    if (p.left != null) child = p.left;
    else if (p.right != null) child = p.right;
    else child = null;

    if (pp == null) tree = child; // 删除的是根节点
    else if (pp.left == p) pp.left = child;
    else pp.right = child;
  }

  // 查找最小值
  public Node findMin() {
    if (tree == null) return null;
    Node p = tree;
    while (p.left != null) {
      p = p.left;
    }
    return p;
  }

  // 查找最大值
  public Node findMax() {
    if (tree == null) return null;
    Node p = tree;
    while (p.right != null) {
      p = p.right;
    }
    return p;
  }

  // 求BST层数 深度优先遍历 leetcode-104
  public int maxDepth(Node root) {
    if (root == null) {
      return 0;
    } else {
      return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
  }

  // 求BST层数 广度优先遍历/层序遍历 类似思路：leetcode-559
  public int maxDepth2(Node root) {
    if (root == null) {
      return 0;
    }
    Node current = root;
    Queue<Node> queue = new LinkedList<>();
    queue.offer(current);
    int front = 0;
    int rear = queue.size();
    int depth = 0;
    while (!queue.isEmpty()) {
      Node tmp = queue.poll();
      front++;
      if (tmp.left != null) {
        queue.offer(tmp.left);
      }
      if (tmp.right != null) {
        queue.offer(tmp.right);
      }
      if (front == rear) { // 遍历完一层
        depth++;
        front = 0;
        rear = queue.size();
      }
    }

    return depth;
  }

  public static class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    BinarySearchTree binarySearchTree = new BinarySearchTree();
    binarySearchTree.insetRecursion(41);
    binarySearchTree.insetRecursion(27);
    binarySearchTree.insetRecursion(65);
    binarySearchTree.insetRecursion(11);
    binarySearchTree.insetRecursion(29);
    binarySearchTree.insetRecursion(10);
    binarySearchTree.insetRecursion(13);
    binarySearchTree.insetRecursion(32);
    binarySearchTree.insetRecursion(31);
    binarySearchTree.insetRecursion(39);
    binarySearchTree.insetRecursion(53);
    binarySearchTree.insetRecursion(91);
    binarySearchTree.insetRecursion(72);

    print(binarySearchTree.tree);
    System.out.println("==================================");
    printSingle(binarySearchTree.findPredecessor(41));
    printSingle(binarySearchTree.findPredecessor(27));
    printSingle(binarySearchTree.findPredecessor(65));
    printSingle(binarySearchTree.findPredecessor(11));
    printSingle(binarySearchTree.findPredecessor(29));
    printSingle(binarySearchTree.findPredecessor(10));
    printSingle(binarySearchTree.findPredecessor(13));
    printSingle(binarySearchTree.findPredecessor(32));
    printSingle(binarySearchTree.findPredecessor(31));
    printSingle(binarySearchTree.findPredecessor(39));
    printSingle(binarySearchTree.findPredecessor(53));
    printSingle(binarySearchTree.findPredecessor(91));
    printSingle(binarySearchTree.findPredecessor(72));
    System.out.println("==================================");
    printSingle(binarySearchTree.findSuccessor(41));
    printSingle(binarySearchTree.findSuccessor(27));
    printSingle(binarySearchTree.findSuccessor(65));
    printSingle(binarySearchTree.findSuccessor(11));
    printSingle(binarySearchTree.findSuccessor(29));
    printSingle(binarySearchTree.findSuccessor(10));
    printSingle(binarySearchTree.findSuccessor(13));
    printSingle(binarySearchTree.findSuccessor(32));
    printSingle(binarySearchTree.findSuccessor(31));
    printSingle(binarySearchTree.findSuccessor(39));
    printSingle(binarySearchTree.findSuccessor(53));
    printSingle(binarySearchTree.findSuccessor(91));
    printSingle(binarySearchTree.findSuccessor(72));
  }

  public static void printSingle(Node p) {
    if (p == null) {
      System.out.println("null");
    } else {
      System.out.println(p.data);
    }
  }
  public static void print(Node p) {
    if (p == null) {
      return;
    }
    print(p.left);
    System.out.print(p.data + " ");
    print(p.right);
  }
}
