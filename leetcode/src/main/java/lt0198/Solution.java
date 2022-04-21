package lt0198;

/**
 * 解题思路：动态规划
 */
public class Solution {
  /**
   * DP
   * @param nums
   * @return
   */
  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    } else if (nums.length == 1) {
      return nums[0];
    }

    int result[] = new int[nums.length];
    result[0] = nums[0];
    result[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      result[i] = Math.max(result[i - 1], result[i - 2] + nums[i]);
    }
    return result[nums.length - 1];
  }

  /**
   * 递归暴力
   * 时间复杂度O(2^n)
   */
  public int rob2(int[] nums) {
    return solve(nums, nums.length - 1);
  }

  private int solve(int[] nums, int index) {
    if (index < 0) {
      return 0;
    }
    // 抢当前这家
    int sum1 = solve(nums, index - 2) + nums[index];
    // 不抢当前这家
    int sum2 = solve(nums, index - 1);
    return Math.max(sum1, sum2);
  }

  /**
   * 递归优化， 去冗余, 时间就是DP
   * 时间复杂度O(n)
   * @param nums
   * @return
   */
  public int rob3(int[] nums) {
    int[] result = new int[nums.length];
    return solve2(nums, nums.length - 1, result);
  }

  private int solve2(int[] nums, int index, int[] result) {
    if (index < 0) {
      return 0;
    }
    if (result[index] > 0) {
      return result[index];
    }

    result[index] = Math.max(solve(nums, index - 2) + nums[index], solve(nums, index - 1));
    return result[index];
  }
}
