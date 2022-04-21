package lt0215;

/**
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 * 我们选择数组区间 A[0...n-1]的最后一个元素 A[n-1]作为 pivot，对数组 A[0...n-1]原地分区，这样数组就分成了三部分，A[0...p-1]、A[p]、A[p+1...n-1]。
 * 如果 p+1=K，那 A[p]就是要求解的元素；如果 K>p+1, 说明第 K 大元素出现在 A[p+1...n-1]区间，我们再按照上面的思路递归地在 A[p+1...n-1]这个区间内查找。
 * 同理，如果 K<p+1, 那我们就在 A[0...p-1]区间查找。
 *
 * 我们再来看，为什么上述解决思路的时间复杂度是 O(n)？第一次分区查找，我们需要对大小为 n 的数组执行分区操作，需要遍历 n 个元素。
 * 第二次分区查找，我们只需要对大小为 n/2 的数组执行分区操作，需要遍历 n/2 个元素。依次类推，分区遍历元素的个数分别为、n/2、n/4、n/8、n/16.……直到区间缩小为 1。
 * 如果我们把每次分区遍历的元素个数加起来，就是：n+n/2+n/4+n/8+...+1。这是一个等比数列求和，最后的和等于 2n-1。
 * 所以，上述解决思路的时间复杂度就为 O(n)。
 * 你可能会说，我有个很笨的办法，每次取数组中的最小值，将其移动到数组的最前面，然后在剩下的数组中继续找最小值，以此类推，执行 K 次，找到的数据不就是第 K 大元素了吗？
 * 不过，时间复杂度就并不是 O(n) 了，而是 O(K * n)。你可能会说，时间复杂度前面的系数不是可以忽略吗？O(K * n) 不就等于 O(n) 吗？这个可不能这么简单地划等号。
 * 当 K 是比较小的常量时，比如 1、2，那最好时间复杂度确实是 O(n)；但当 K 等于 n/2 或者 n 时，这种最坏情况下的时间复杂度就是 O(n2) 了。
 */
class Solution {
  public int findKthLargest(int[] nums, int k) {
    // 这里我们从大到小排序数组，第K大值就是下标为k-1的数值
    return findKth(nums, 0, nums.length - 1, k - 1);
  }

  public int findKth(int[] nums, int left, int right, int k) {
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
      return findKth(nums, low + 1, right, k - kth - 1);
    } else {
      return findKth(nums, left, low - 1, k);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int result = solution.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4);
    System.out.println(result);
  }
}
