package lt0300;

public class Solution {
  /**
   * 解答一
   * 首先我们试着应用动态规划的思想去解决这个问题。假设为排序的数组为A, 动态规划记录表为数组dp, dp[i]表示以A[i]结尾的最长递增子序列长度，
   * 如A=[10,9,2,5,3,7,101,18]， 有dp=[1,1,1,2,2,3,4,4]。问题转化为根据dp[0...i-1]求解dp[i]。
   *
   * @param nums
   * @return
   */
  public int lengthOfLIS1(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
      }
    }
    return dp[nums.length - 1];
  }

  /**
   * 解答二
   * 我们通过改变记录表中元素的意义进一步优化效率。 假设我们记录表的名字叫tails, tails的索引i并不表示A中第i个元素索引，
   * 而是用来表示当前递增子序列的长度i+1。tails[i]表示当前递增子序列长度为i+1时，子序列结尾数的最小值(因为有多个长度为i+1的递增子序列)。
   * 遍历数组A时，对于某个元素e，找到e在tails中的位置，假设tails目前长度为size，
   * <p>
   * 如果找到某个j,使得tails[j-1]<e<=tails[j]， 更新tails[j] = e;
   * 否则e>tails[tails.size], 那么tails[size+1] = e，size也自然增加1;
   * 显然tails是有序的，可以使用二分查找e在tails的中位置。代码如下。该算法时间复杂度为O(nlogn)
   *
   * @param nums
   * @return
   */
  public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = -1;
    if (nums.length > 0) {
      tails[++size] = nums[0];
    }
    for (int i = 1; i < nums.length; i++) {
      int j = binarySearch(tails, 0, size, nums[i]);
      if (j <= size) {
        tails[j] = nums[i];
      } else {
        tails[++size] = nums[i];
      }
    }
    return size + 1;
  }

  /**
   * 二分搜索，返回arr中第一个不小于val的元素索引
   *
   * @param arr
   * @param begin
   * @param end
   * @param val
   * @return
   */
  private static int binarySearch(int[] arr, int begin, int end, int val) {
    while (begin < end) {
      int mid = (begin + end) / 2;
      if (arr[mid] < val) {
        begin = mid + 1;
      } else {
        end = mid;
      }
    }
    return arr[begin] >= val ? begin : Integer.MAX_VALUE;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.lengthOfLIS1(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(solution.lengthOfLIS1(new int[]{7, 7, 7, 7, 7, 7, 7}));
    System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    System.out.println(solution.lengthOfLIS1(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
  }
}
