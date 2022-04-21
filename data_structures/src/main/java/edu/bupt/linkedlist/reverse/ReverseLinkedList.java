package edu.bupt.linkedlist.reverse;

/**
 * 参考: https://blog.csdn.net/beiyetengqing/article/details/7596554
 */
public class ReverseLinkedList {
  // 非递归
  public Node reverseI(Node current) {
    Node previousNode = null;
    Node nextNode = null;
    while (current != null) {
      nextNode = current.next;
      current.next = previousNode;
      previousNode = current;
      current = nextNode;
    }

    return previousNode;
  }

  // 递归
  public Node reverseII(Node current) {
    if (current == null || current.next == null) {
      return current;
    }

    Node nextNode = current.next;
    current.next = null;
    Node reverseNode = reverseII(nextNode);
    nextNode.next = current;
    return reverseNode;
  }
  
  public static void main(String[] args) {
    Node node1 = new Node(1, null);
    Node node2 = new Node(2, node1);
    Node node3 = new Node(3, node2);
    Node node4 = new Node(4, node3);
    Node current = new Node(5, node4);

    ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
//    Node reverse = reverseLinkedList.reverseI(current);
    Node reverse = reverseLinkedList.reverseII(current);
    while (reverse != null) {
      System.out.println(reverse.value);
      reverse = reverse.next;
    }
  }
}

class Node {
  int value;
  Node next;

  public Node(int value, Node next) {
    this.value = value;
    this.next = next;
  }

  public int getValue() {
    return value;
  }

  public Node getNext() {
    return next;
  }
}
