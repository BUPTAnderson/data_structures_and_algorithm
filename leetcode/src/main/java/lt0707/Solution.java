package lt0707;

/**
 * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
 * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
 * Created by Anderson on 2021/12/25
 */

class MyLinkedList {
  class Node {
    int val;
    Node next;

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  private Node head = null;
  private Node tail = null;
  private int maxIndex = -1; // 最后一个节点的索引

  public MyLinkedList() {
  }

  public int get(int index) {
    if (index > maxIndex || index < 0) {
      return -1;
    }

    Node current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.val;
  }

  public void addAtHead(int val) {
    if (maxIndex == -1) {
      head = tail = new Node(val, null);
    } else {
      Node tmp = new Node(val, head);
      head = tmp;
    }
    maxIndex++;
  }

  public void addAtTail(int val) {
    if (maxIndex == -1) {
      head = tail = new Node(val, null);
    } else {
      tail.next = new Node(val, null);
      tail = tail.next;
    }
    maxIndex++;
  }

  public void addAtIndex(int index, int val) {
    if (index > maxIndex + 1) {
      return;
    }

    if (index == 0) {
      addAtHead(val);
    } else if (index == maxIndex + 1) {
      addAtTail(val);
    } else {
      Node current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node tmp = new Node(val, current.next);
      current.next = tmp;
      maxIndex++;
    }
  }

  public void deleteAtIndex(int index) {
    if (index > maxIndex || maxIndex == -1) {
      return;
    }
    if (index == 0) {
      head = head.next;
      if (head == null) {
        tail = null;
      }
    } else {
      Node current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      current.next = current.next.next;
      if (current.next == null) { // 删除的是最后一个节点，修改tail
        tail = current;
      }
    }
    maxIndex--;
  }
}

public class Solution {
  public static void main(String[] args) {
    /**
     * [11],[80],[31],[13,23],[17],[4],[10,0],[21],[73],[22],[24,37],[14],[97],[8],[6],[17],[50],[28],[76],[79],[18],[30],[5],[9],[83],[3],[40],[26],[20,90],[30],[40],[56],[15,23],[51],[21],[26],[83],[30],[12],[8],[4],[20],[45],[10],[56],[18],[33],[2],[70],[57],[31,24],[16,92],[40],[23],[26],[1],[92],[3,78],[42],[18],[39,9],[13],[33,17],[51],[18,95],[18,33],[80],[21],[7],[17,46],[33],[60],[26],[4],[9],[45],[38],[95],[78],[54],[42,86]]
     */
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(84);
    myLinkedList.addAtTail(2);
    myLinkedList.addAtTail(39);
    myLinkedList.get(3);
    myLinkedList.get(1);
    myLinkedList.addAtTail(42);
    myLinkedList.addAtIndex(1,80);
    myLinkedList.addAtHead(14);
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(53);
    myLinkedList.addAtTail(98);
    myLinkedList.addAtTail(19);
    myLinkedList.addAtTail(12);
    myLinkedList.get(2);
    myLinkedList.addAtHead(16);
    myLinkedList.addAtHead(33);
    myLinkedList.addAtIndex(4,17);
    myLinkedList.addAtIndex(6,8);
    myLinkedList.addAtHead(37);
    myLinkedList.addAtTail(43);
    myLinkedList.deleteAtIndex(11);
    myLinkedList.addAtHead(80);
    myLinkedList.addAtHead(31);
    myLinkedList.addAtIndex(13,23);
    myLinkedList.addAtTail(17);
    myLinkedList.get(4);
    myLinkedList.addAtIndex(10,0);
    myLinkedList.addAtTail(21);
    myLinkedList.addAtHead(73);
    myLinkedList.addAtHead(22);
    myLinkedList.addAtIndex(24,37);
    myLinkedList.addAtTail(14);
    myLinkedList.addAtHead(97);
    myLinkedList.addAtHead(8);
    myLinkedList.get(6);
    myLinkedList.deleteAtIndex(17);
    myLinkedList.addAtTail(50);
    myLinkedList.addAtTail(28);
    myLinkedList.addAtHead(76);
    myLinkedList.addAtTail(79);
    myLinkedList.get(18);
    myLinkedList.deleteAtIndex(30);
    myLinkedList.addAtTail(5);
    myLinkedList.addAtHead(9);
    myLinkedList.addAtTail(83);
    myLinkedList.deleteAtIndex(3);
    myLinkedList.addAtTail(40);
    myLinkedList.deleteAtIndex(26);
    myLinkedList.addAtIndex(20,90);
    myLinkedList.deleteAtIndex(30);
    myLinkedList.addAtTail(40);
    myLinkedList.addAtHead(56);
    myLinkedList.addAtIndex(15,23);
    myLinkedList.addAtHead(51);
    myLinkedList.addAtHead(21);
    myLinkedList.get(26);
    myLinkedList.addAtHead(83);
    myLinkedList.get(30);
    myLinkedList.addAtHead(12);
    myLinkedList.deleteAtIndex(8);
    myLinkedList.get(4);
    myLinkedList.addAtHead(20);
    myLinkedList.addAtTail(45);
    myLinkedList.get(10);
    myLinkedList.addAtHead(56);
    myLinkedList.get(18);
    myLinkedList.addAtTail(33);
    myLinkedList.get(2);
    myLinkedList.addAtTail(70);
    myLinkedList.addAtHead(57);
    myLinkedList.addAtIndex(31,24);
    myLinkedList.addAtIndex(16,92);
    myLinkedList.addAtHead(40);
    myLinkedList.addAtHead(23);
    myLinkedList.deleteAtIndex(26);
    myLinkedList.get(1);
    myLinkedList.addAtHead(92);
    myLinkedList.addAtIndex(3, 78);
    myLinkedList.addAtTail(42);
    myLinkedList.get(18);
    myLinkedList.addAtIndex(39,9);
    myLinkedList.get(13);
    myLinkedList.addAtIndex(33,17);
    myLinkedList.get(51);
    myLinkedList.addAtIndex(18,95);
    myLinkedList.addAtIndex(18,33);
    myLinkedList.addAtHead(80);
    myLinkedList.addAtHead(21);
    myLinkedList.addAtTail(7);
    myLinkedList.addAtIndex(17,46);
    myLinkedList.get(33);
    myLinkedList.addAtHead(60);
    myLinkedList.addAtTail(26);
    myLinkedList.addAtTail(4);
    myLinkedList.addAtHead(9);
    myLinkedList.get(45);
    myLinkedList.addAtTail(38);
    // ],[95],[78],[54],[42,86]]
    myLinkedList.addAtHead(95);
    myLinkedList.addAtTail(78);
    myLinkedList.get(54);
    myLinkedList.addAtIndex(42,86);
  }
}
