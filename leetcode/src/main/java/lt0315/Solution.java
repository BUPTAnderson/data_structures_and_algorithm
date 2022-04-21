package lt0315;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基本思路是对nums[]做归并排序。为了记录结果，我们需要保留原始数组中每个数字的索引。所以我们不是对nums中的数字进行排序，而是对每个数字的索引进行排序。
 * 例如：nums = [5,2,6,1], indexes = [0,1,2,3］
 * 排序后：indexes = [3,1,0,2] 。
 * <p>
 * 在做合并部分时，假设我们要合并left[]和right[]，left[]和right[]已经被排序。
 * 我们保留一个rightcount来记录我们从right[]添加了多少个数字，并保留一个数组count[]来记录结果。
 * 当我们将一个数字从right[]移入新的排序数组时，我们将rightcount增加1。
 * 当我们将一个数字从left[]移入新的排序数组时，我们将count[数字的索引]增加rightcount。
 *
 * 参考：https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
 */
public class Solution {
  public List<Integer> countSmaller(int[] nums) {
    Integer[] result = new Integer[nums.length];

    int[] indexes = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      indexes[i] = i;
    }

    mergeSort(nums, indexes, result, 0, nums.length - 1);

    return Arrays.asList(result);
  }

  private void mergeSort(int[] nums, int[] indexes, Integer[] result, int start, int end) {
    if (start < end) {
      int mid = (start >> 1) + (end >> 1);
      mergeSort(nums, indexes, result, start, mid);
      mergeSort(nums, indexes, result, mid + 1, end);
      merge(nums, indexes, result, start, mid, end);
    }
  }

  private void merge(int[] nums, int[] indexes, Integer[] result, int start, int mid, int end) {
    int[] tmp_indexes = new int[end - start + 1];
    int left = start;
    int right = mid + 1;
    int tmp_index = 0;
    int rightcount = 0;
    while (left <= mid && right <= end) {
      if (nums[indexes[right]] < nums[indexes[left]]) { // 左边有比右边小的才计数，相等的情况不计数。
        tmp_indexes[tmp_index] = indexes[right];
        rightcount++;
        right++;
      } else {
        tmp_indexes[tmp_index] = indexes[left];
        result[indexes[left]] += rightcount;
        left++;
      }

      tmp_index++;
    }

    while (left <= mid) {
      tmp_indexes[tmp_index] = indexes[left];
      result[indexes[left]] += rightcount;
      tmp_index++;
      left++;
    }

    while (right <= end) {
      tmp_indexes[tmp_index++] = indexes[right++];
    }

    for (int i = start; i <= end; i++) {
      indexes[i] = tmp_indexes[i - start];
    }
  }

  public static void main(String[] args) {
    int[] test = new int[]{5, 2, 6, 1};
    Solution solution = new Solution();
    List<Integer> result = solution.countSmaller(test);
    result.forEach(System.out::println);
  }
}
