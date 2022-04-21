package lt0109;

import common.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * <p>
 * 解题思路：
 * 中间节点是跟节点， 左半边部分是左子树， 右半边部分是右子树
 * 左子树的中间节点是左子树的根节点， 右子树的中间节点是右子树的跟节点，以此类推
 * 类似二分查找
 * <p>
 * 问题关键是二分查找可以随机访问，logN, 而这里是链表，没法随机访问
 * 如果每次先通过快慢指针遍历到中间节点，然后左右部分再递归的调用，则需要执行logN轮，每轮左右部分的复杂度是O(n), 总的时间复杂度是O(nlogn)
 * 一种方法是将链表先转成ArrayList 时间复杂度=O(n) + logN = O(n)
 * 另一种方法是
 */
public class Solution {
  // O(nlogn)
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      TreeNode treeNode = new TreeNode(head.val);
      return treeNode;
    }
    // 中间节点是中间偏右的节点 [1,2,3]中间节点2,  [1,2]中间节点2, 这里为了切分方便，[1,2,3]我们找的是1， [1,2]我们找的也是1
    ListNode slow = head, fast = head.next.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    TreeNode root = new TreeNode(slow.next.val);
    ListNode temp = slow.next.next;
    slow.next = null;
    root.left = sortedListToBST(head);
    root.right = sortedListToBST(temp);
    return root;
  }

  // list -> arraylist
  public TreeNode sortedListToBSTII(ListNode head) {
    List<Integer> arr = new ArrayList<>();
    while (head != null) {
      arr.add(head.val);
      head = head.next;
    }
    return buildBST(arr, 0, arr.size() - 1);
  }

  TreeNode buildBST(List<Integer> list, int left, int right) {
    if (left > right) return null;
    int mid = left + (right - left) / 2;
    TreeNode root = new TreeNode(list.get(mid));
    root.left = buildBST(list, left, mid - 1);
    root.right = buildBST(list, mid + 1, right);
    return root;
  }

  // 递归
  ListNode head;

  public TreeNode sortedListToBSTIII(ListNode head) {
    this.head = head;
    return buildBST(0, length(head) - 1);
  }

  TreeNode buildBST(int left, int right) {
    if (left > right) return null;
    int mid = (left + right + 1) / 2;
    TreeNode leftNode = buildBST(left, mid - 1);

    TreeNode root = new TreeNode(head.val); // root node is the mid node
    head = head.next; // go next

    root.left = leftNode;
    root.right = buildBST(mid + 1, right);
    return root;
  }

  int length(ListNode head) {
    int ans = 0;
    while (head != null) {
      head = head.next;
      ans++;
    }
    return ans;
  }

  public static void main(String[] args) {
    ListNode ln1 = new ListNode(9, null);
    ListNode ln2 = new ListNode(5, ln1);
    ListNode ln3 = new ListNode(0, ln2);
    ListNode ln4 = new ListNode(-3, ln3);
    ListNode ln5 = new ListNode(-10, ln4);
    Solution solution = new Solution();
    TreeNode result = solution.sortedListToBSTIII(ln5);
    printTree(result);
  }

  public static void printTree(TreeNode treeNode) {
    if (treeNode == null) {
      return;
    }
    LinkedList<TreeNode> list = new LinkedList();
    list.offer(treeNode);
    while (!list.isEmpty()) {
      TreeNode node = list.poll();
      System.out.println(node.val);
      if (node.left != null) {
        list.offer(node.left);
      }
      if (node.right != null) {
        list.offer(node.right);
      }
    }
  }
}


class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}