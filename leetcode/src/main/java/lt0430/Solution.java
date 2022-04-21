package lt0430;

class Solution {
  public Node flatten(Node head) {
    Node current = head;
    while (current != null) {
      if (current.child != null) {
        Node next = current.next;
        current.next = current.child;
        current.child = null;
        current.next.prev = current;
        Node tail = processChild(current.next);
        tail.next = next;
        if (next != null) {
          next.prev = tail;
        }
        current = next;
      } else {
        current = current.next;
      }
    }
    return head;
  }

  private Node processChild(Node child) {
    Node current = child, tail = child, next;
    while (current != null) {
      next = current.next;
      if (current.child != null) {

        tail = processChild(current.child);
        tail.next = next;
        if (next != null) {
          next.prev = tail;
        }

        current.next = current.child;
        current.child = null;
        current.next.prev = current;
      } else {
        tail = current;
      }
      current = next;
    }
    return tail;
  }
}


class Node {
  public int val;
  public Node prev;
  public Node next;
  public Node child;
}