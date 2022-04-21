package lt0069;

/**
 * 推荐mySqrt2的解法
 */
public class Solution {
  public int mySqrt(int x) {
    if (x == 0 || x == 1) {
      return x;
    }

    long low = 0, high = x / 2;
    while (low <= high) {
      long mid = low + ((high - low) >> 1);

      long value = mid * mid;
      if (value <= x && (mid + 1) * (mid + 1) > x) { // 下一个解法改进成除法
        return (int) mid;
      } else if (value > x) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return 0;
  }

  // Runtime: 1 ms, faster than 99.98% of Java online submissions for Sqrt(x).
  public int mySqrt2(int x) {
    if (x == 0) {
      return x;
    }

    int low = 1, high = x / 2; // low从1开始可以兼容x==1，并且防止mid=0时报除0异常
    while (low <= high) {
      int mid = low + ((high - low) >> 1);
      if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
        return mid;
      } else if (mid > x / mid) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  // 牛顿法：https://leetcode.com/problems/sqrtx/discuss/25198/3-JAVA-solutions-with-explanation
  public int mySqrt3(int x) {
    if (x == 0) return 0;
    long i = x;
    while (i > x / i)
      i = (i + x / i) / 2;
    return (int) i;
  }

  // bit,没看懂 https://leetcode.com/problems/sqrtx/discuss/25048/Share-my-O(log-n)-Solution-using-bit-manipulation
  public int sqrt3(int x) {
    if (x == 0) {
      return 0;
    }
    int h = 0, tmp = 1 << h;
    while (tmp <= x / tmp) { // firstly, find the most significant bit
      h++;
      tmp = 1 << h;
    }
    h--;
    int b = h - 1;
    int res = (1 << h);
    while (b >= 0) {  // find the remaining bits
      if ((long) (res | (1 << b)) * (long) (res | (1 << b)) <= x)
        res |= (1 << b);
      b--;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.mySqrt2(0));
    System.out.println(solution.mySqrt2(1));
    System.out.println(solution.mySqrt2(2));
    System.out.println(solution.mySqrt2(3));
    System.out.println(solution.mySqrt2(4));
    System.out.println(solution.mySqrt2(2147395599));
    System.out.println(solution.mySqrt2(2147395600));
    System.out.println(solution.mySqrt2(Integer.MAX_VALUE));
  }
}
