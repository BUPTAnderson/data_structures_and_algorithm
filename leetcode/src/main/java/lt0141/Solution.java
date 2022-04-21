package lt0141;

import common.ListNode;

public class Solution {
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    // 这里这么写是为了和142题保持一致，正常开始应该是 slow = fast = head; 下面的赋值相当于，slow和fast按照自己的规则向后执行了一次。即slow向后走一步，fast向后走2步
    // 这里也可以 ListNode slow = head, fast = head; 这样的话，while中需要将19~23行代码放到if (slow == fast) 语句前面，因为刚赋值slow= fast = head, 先比较的话肯定相等。
    ListNode slow = head.next;
    ListNode fast = head.next.next;

    while (fast != null) { // 只需判断fast即可
      if (slow == fast) {
        return true;
      }
      slow = slow.next;
      fast = fast.next;
      if (fast != null) {
        fast = fast.next;
      }
    }
    return false;
  }
}
