package lt0215;

public class Solution2 {
  public int findKthLargest(int[] nums, int k) {
    int left = 0, right = nums.length - 1;
    k--;
    while (true) {
      int pivot = nums[right], low = left, high = right;
      while (low < high) {
        while (low < high && nums[low] >= pivot) {
          low++;
        }
        nums[high] = nums[low];
        while (low < high && nums[high] <= pivot) {
          high--;
        }
        nums[low] = nums[high];
      }
      nums[low] = pivot;
      int kth = low - left;
      if (kth == k) { // low坐标的值就是当前区间内的(low - left + 1)大的值
        return pivot;
      } else if (kth < k) {
        left = low + 1;
        k = k - kth - 1;
      } else {
        right = low -1;
      }
    }
  }

  public static void main(String[] args) {
    Solution2 solution2 = new Solution2();
    solution2.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
  }
}
