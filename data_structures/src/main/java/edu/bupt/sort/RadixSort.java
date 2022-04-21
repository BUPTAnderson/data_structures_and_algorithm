package edu.bupt.sort;

/**
 *     基数排序对要排序的数据是有要求的，需要可以分割出独立的“位”来比较，而且位之间有递进的关系，如果 a 数据的高位比 b 数据大，那剩下的低位就不用比较了。
 * 除此之外，每一位的数据范围不能太大，要可以用线性排序算法来排序，否则，基数排序的时间复杂度就无法做到 O(n) 了。
 *
 *     假设我们有 10 万个手机号码，希望将这 10 万个手机号码从小到大排序，你有什么比较快速的排序方法呢？
 *     借助稳定排序算法，这里有一个巧妙的实现思路。还记得我们第 11 节中，在阐述排序算法的稳定性的时候举的订单的例子吗？我们这里也可以借助相同的处理思路，
 * 先按照最后一位来排序手机号码，然后，再按照倒数第二位重新排序，以此类推，最后按照第一位重新排序。经过 11 次排序之后，手机号码就都有序了。
 *     为什么从前向后排？
 *     从前往后排：排好了第一位数，排第二位数的时候，还需要保证第一位数是有序的。就相当于排第一位数时，需要分桶，1 在第一个桶，2 在第二个桶，然后在每个桶里面再进行第二位数的排序。
 * 比如有三个数： 132 213 123 根据第一位排序的结果： 132 123 213 根据第二位排序的结果（假设不考虑第一位）： 213 123 132 那这样就错了，需要基于第一位的排序结果再进行排序才行：
 * 123 132 213 从后往前排：排好了最后一位数，排倒数第二位数时，不需要再考虑最后一位数了。反正是左边的数优先级更高。
 *     为什么要基于稳定排序算法？
 *     还是上面的例子，排序完第三位和第二位数后是 213 123 132 然后按照第一位排序，稳定的排序可以保证排完序后 123仍然在132前面，这样就不会打乱我们对第二问数值的排序了。
 *
 * Created by Anderson on 2021/3/11
 */
public class RadixSort {
  /**
   * 基数排序
   *
   * @param arr
   */
  public static void radixSort(int[] arr) {
    int max = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }

    // 从个位开始，对数组arr按"指数"进行排序
    for (int exp = 1; max / exp > 0; exp *= 10) {
      countingSort(arr, exp);
    }
  }

  /**
   * 计数排序-对数组按照"某个位数"进行排序
   *
   * @param arr
   * @param exp 指数
   */
  public static void countingSort(int[] arr, int exp) {
    if (arr.length <= 1) {
      return;
    }

    // 计算每个元素的个数
    int[] c = new int[10];
    for (int i = 0; i < arr.length; i++) {
      c[(arr[i] / exp) % 10]++;
    }

    // 计算排序后的位置
    for (int i = 1; i < c.length; i++) {
      c[i] += c[i - 1];
    }

    // 临时数组r，存储排序之后的结果
    int[] r = new int[arr.length];
    for (int i = arr.length - 1; i >= 0; i--) {
      r[c[(arr[i] / exp) % 10] - 1] = arr[i];
      c[(arr[i] / exp) % 10]--;
    }

    for (int i = 0; i < arr.length; i++) {
      arr[i] = r[i];
    }
  }

  public static void main(String[] args) {
    int[] tmp = new int[]{123, 213, 132};
    radixSort(tmp);
    for (int i : tmp) {
      System.out.println(i);
    }
  }
}
