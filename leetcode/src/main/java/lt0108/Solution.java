package lt0108;

import common.TreeNode;

public class Solution {
  /**
   * 思路：类似于二分查找，中间节点是root，左右部分分别是左右子树，继续递归处理。
   * @param nums
   * @return
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    return buidTree(nums, 0, nums.length);
  }

  private TreeNode buidTree(int[] nums, int start, int end) {
    if (start >= end) {
      return null;
    }

    int mid = (end + start) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = buidTree(nums, start, mid);
    root.right = buidTree(nums, mid + 1, end);
    return root;
  }
}
