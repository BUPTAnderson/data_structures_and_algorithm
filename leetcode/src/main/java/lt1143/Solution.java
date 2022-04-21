package lt1143;
/**
 * 最长公共子序列LCS
 * 最长公共子序列LCS（longest common subsequence）和最长公共子串（longest common substring）不是一回事儿。
 * 什么是子序列呢？即一个给定的序列的子序列，就是将给定序列中零个或多个元素去掉之后得到的结果。什么是子串呢？给定串中任意个连续的字符组成的子序列称为该串的子串。
 * 给定的字符序列： {a,b,c,d,e,f,g,h}，
 * 它的子序列示例： {a,c,e,f} 即元素b,d,g,h被去掉后，保持原有的元素序列所得到的结果就是子序列。同理，{a,h},{c,d,e}等都是它的子序列。
 * 它的字串示例：{c,d,e,f} 即连续元素c,d,e,f组成的串是给定序列的字串。同理，{a,b,c,d},{g,h}等都是它的字串。
 *
 * 这个问题说明白后，最长公共子序列（以下都简称LCS）就很好理解了。
 * 给定序列s1={1,3,4,5,6,7,7,8},s2={3,5,7,4,8,6,7,8,2}，s1和s2的相同子序列，且该子序列的长度最长，即是LCS。
 * s1和s2的其中一个最长公共子序列是 {3,4,6,7,8}
 */
public class Solution {

  public int longestCommonSubsequence(String text1, String text2) {
    if (text1.length() <= 0 || text2.length() <= 0) {
      return 0;
    }

    int index1 = text1.length() - 1;
    int index2 = text2.length() - 1;
    // 下面是递推公式
    if (text1.charAt(index1) == text2.charAt(index2)) {
      return longestCommonSubsequence(text1.substring(0, index1), text2.substring(0, index2)) + 1;
    } else {
      int num1 = longestCommonSubsequence(text1.substring(0, index1), text2);
      int num2 = longestCommonSubsequence(text1, text2.substring(0, index2));
      return num1 > num2 ? num1 : num2;
    }
  }

  /**
   * DP 递归方式
   * Time & space: O(m * n)
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence2(String text1, String text2) {
    int[][] results = new int[text1.length()][text2.length()];
    lcs(text1, text2, results);
    return results[text1.length() - 1][text2.length() - 1];
  }

  private int lcs(String text1, String text2, int[][] results) {
    if (text1.length() <= 0 || text2.length() <= 0) {
      return 0;
    }

    int index1 = text1.length() - 1;
    int index2 = text2.length() - 1;
    // 下面是递推公式
    int max = -1;
    if (text1.charAt(index1) == text2.charAt(index2)) {
      max = longestCommonSubsequence(text1.substring(0, index1), text2.substring(0, index2)) + 1;
    } else {
      int num1 = longestCommonSubsequence(text1.substring(0, index1), text2);
      int num2 = longestCommonSubsequence(text1, text2.substring(0, index2));
      max = num1 > num2 ? num1 : num2;
    }

    results[text1.length() - 1][text2.length() - 1] = max;
    return max;
  }

  /**
   * DP 直接推导结果集
   * Time & space: O(m * n)
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence3(String text1, String text2) {
    int[][] results = new int[text1.length()][text2.length()];
    if (text1.charAt(0) == text2.charAt(0)) {
      results[0][0] = results[1][0] = results[0][2] = 1;
    }
    for (int i = 1; i < text1.length(); i++) {
      for (int j = 1; j < text2.length(); j++) {
        if (text1.charAt(i) == text2.charAt(j)) {
          results[i][j] = results[i - 1][j - 1] + 1;
        } else {
          results[i][j] = Math.max(results[i - 1][j], results[i][j - 1]);
        }
      }
    }

    return results[text1.length() - 1][text2.length() - 1];
  }

  /**
   * DP 直接推导结果集
   * Time: O(m * n), space: O(min(m, n))
   * 空间压缩：
   * 二维dp数组中每个格子内的值会依赖它的左边，上边和左上边，和其他的值无关。即我们可以一行一行的计算
   * 而左上角的值有可能会被上一步计算的时候就被替换掉了，所以必须要先保存下来。
   * 链接： https://www.cnblogs.com/HuangYJ/p/14232685.html
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence4(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    if (m < n) {
      return longestCommonSubsequence4(text2, text1);
    }
    int[] dp = new int[n];
    // 初始化第一行
    dp[0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
    for (int j = 1; j < n; j++) {
      dp[j] = Math.max(dp[j-1], text1.charAt(0) == text2.charAt(j) ? 1 : 0);
    }
    // pre保存左上角的值(相当于d[i-1][j-1])，dp[j-1]保存左边的值(相当于d[i-1][j])，dp[j]保存上边的值(相当于d[i][j-1])，根据这3个值求当前dp[j]的值(相当于d[i][j])
    // dp[j](相当于d[i][j-1]) 会被覆盖掉，所以要保存下来，因为对下一个计算的值来说dp[j](相当于d[i][j-1])有相当于pre了。
    for (int i = 1; i < m; i++) { // 开始算第二行(i=1)
      int pre = dp[0]; // 先保存左上角的值
      // 单独算第一列
      dp[0] = Math.max(dp[0], text1.charAt(i) == text2.charAt(0) ? 1 : 0);
      for (int j = 1; j < n; j++) {
        int temp = dp[j];  // dp[j](相当于d[i][j-1]) 这个值会被替换，所以替换之前要把他保存下来
        if (text1.charAt(i) == text2.charAt(j)) {
          dp[j] = pre + 1;  // 左上
        }else {
          dp[j] = Math.max(dp[j-1], dp[j]);  // 左 或者 上
        }
        pre = temp; // 保存左上角的值为
      }
    }
    return dp[n-1];
  }

  /**
   * DP 直接推导结果集 和longestCommonSubsequence4一致，只不过更简洁些，这里通过多申请一个空间来处理边界问题
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence5(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    if (m < n) {
      return longestCommonSubsequence5(text2, text1);
    }
    int[] dp = new int[n + 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0, preRow = 0, preRowPreCol = 0; j < n; j++) { // 这里结果集整体向右偏移了一位，可以避免j=0时要对比j-1（-1）这种数组越界的处理
        preRowPreCol = preRow; // preRowPreCol是dp[j+1]左上角的值
        preRow = dp[j + 1]; // preRow是dp[j+1]上面的值， dp[j]是dp[j+1]左边的值
        dp[j + 1] = text1.charAt(i) == text2.charAt(j) ? preRowPreCol + 1 : Math.max(dp[j], preRow); //
      }
    }

    return dp[n];
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.longestCommonSubsequence("abc", "abc"));
    System.out.println(solution.longestCommonSubsequence2("abc", "abc"));
    System.out.println(solution.longestCommonSubsequence3("abc", "abc"));
    System.out.println(solution.longestCommonSubsequence5("abc", "abc"));
    System.out.println(solution.longestCommonSubsequence("abcde", "ace"));
    System.out.println(solution.longestCommonSubsequence2("abcde", "ace"));
    System.out.println(solution.longestCommonSubsequence3("abcde", "ace"));
    System.out.println(solution.longestCommonSubsequence5("abcde", "ace"));
    System.out.println(solution.longestCommonSubsequence("abc", "def"));
    System.out.println(solution.longestCommonSubsequence2("abc", "def"));
    System.out.println(solution.longestCommonSubsequence3("abc", "def"));
    System.out.println(solution.longestCommonSubsequence5("abc", "def"));
  }
}
