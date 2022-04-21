package lt0725;

import common.ListNode;

public class Solution {
  public ListNode[] splitListToParts(ListNode head, int k) {
    ListNode current = head, tmp;
    int length = 0;
    while (current != null) {
      current = current.next;
      length++;
    }
    ListNode[] result = new ListNode[k];
    if (length <= k) {
      current = head;
      int i = 0;
      while (current != null) {
        result[i] = current;
        current = current.next;
        result[i].next = null;
        i++;
      }

      return result;
    }

    int num = length / k;
    int other = length % k;
    current = head;
    for (int i = 0; i < k; i++) {
      if (other > 0) {
        result[i] = current;
        for (int j = 0; j < num; j++) {
          current = current.next;
        }
        other--;
      } else {
        result[i] = current;
        for (int j = 1; j < num; j++) {
          current = current.next;
        }
      }

      tmp = current;
      current = current.next;
      tmp.next = null;
    }

    return result;
  }
}
