package edu.bupt;

public class Solution {
  public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > nums[high]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    int offset = low;
    low = 0;
    high = nums.length - 1;
    while (low < high) {
      int mid = low + (high - low)/2;
      int real = (mid + offset) % nums.length;
      if (nums[real] > target) {
        high = mid - 1;
      } else if (nums[real] < target) {
        low = mid + 1;
      } else {
        return real;
      }
    }
    return -1;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.search(new int[]{4,5,6,7,0,1,2}, 0));;
  }
}
