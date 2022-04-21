package edu.bupt.linkedlist;

/**
 * 参考：https://blog.csdn.net/beiyetengqing/article/details/7622306
 * 参考链接给出的解答有错误
 * 当h1 或 h2 为null时不能直接返回，因为h1 或 h2 中可能有重复的数据
 *
 */
public class MergeListWithoutDuplicate {
  public static Node mergeSortedListWithouDuplicates(Node h1, Node h2) {
    Node newHead = null;
    if (h1 == null) {
      newHead = h2;
    } else if (h2 == null) {
      newHead = h1;
    } else {
      newHead = (h1.data <= h2.data) ? h1 : h2;
    }
    Node pre = newHead;
    Node small = null;
    while (h1 != null && h2 != null) {
      if (h1.data < h2.data) {
        small = h1;
        h1 = h1.next;
      } else {
        small = h2;
        h2 = h2.next;
      }
      if (pre.data != small.data) {
        pre.next = small;
        pre = small;
      }
    }

    while (h1 != null) {
      if (pre.data != h1.data) {
        pre.next = h1;
        pre = h1;
      }
      h1 = h1.next;
    }
    while (h2 != null) {
      if (pre.data != h2.data) {
        pre.next = h2;
        pre = h2;
      }
      h2 = h2.next;
    }
    return newHead;
  }

  public static void main(String[] args) {

  }
}
