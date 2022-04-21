package lt0322;

public class Solution {


  /**
   * 传统递归解法: 复杂度 k^n k是零钱的种类
   * 存在很多冗余
   *
   * @param coins
   * @param amount
   * @return
   */
  public int coinChange(int[] coins, int amount) {
    if (amount < 0) {
      return -1;
    }
    if (amount == 0) {
      return 0;
    }

    int min = Integer.MAX_VALUE;
    for (int coin : coins) { // 开始找零，找的第一个零钱是coin 分别处理
      int result = coinChange(coins, amount - coin);
      if (result >= 0 && result < min) {
        min = result + 1;
      }
    }
    return min < Integer.MAX_VALUE ? min : -1;
  }

  /**
   * DP 这里还是采用递归的方式
   *
   * @param coins
   * @param amount
   * @return
   */

  public int coinChange2(int[] coins, int amount) {
    if (amount <= 1) {
      return 0;
    }
    int[] results = new int[amount + 1];
    coinChangeProcee(coins, amount, results);
    return results[amount] == 0 ? -1 : results[amount];
  }

  private int coinChangeProcee(int[] coins, int amount, int[] results) {
    if (amount < 0) {
      return -1;
    }
    if (amount == 0) {
      return 0;
    }
    if (results[amount] != 0) {
      return results[amount];
    }

    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int result = coinChangeProcee(coins, amount - coin, results);
      if (result >= 0 && result < min) {
        min = result + 1;
      }
    }
    results[amount] = min;
    return results[amount];
  }

  /**
   * DP 非递归，直接推导出结果集 强烈推荐这种方式。
   *
   * @param coins
   * @param amount
   * @return
   */
  public int coinChange3(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    int[] results = new int[amount + 1];
    for (int res = 1; res <= amount; res++) {
      int min = -1;
      for (int coin : coins) {
        if (res >= coin && results[res - coin] != -1) { // 这里注意，我们从res=1开始，隐含了results[0]初始化为0
          int current = results[res - coin] + 1;
//          min = min < 0 ? current : (current < min ? current : min); // 这一行等效为下面的if语句
          if (min < 0 || current < min) {
            min = current;
          }
        }
      }
      results[res] = min;
    }

    return results[amount];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

//    int result = solution.coinChange(new int[]{1, 2, 5}, 11);
//    int result = solution.coinChange(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864);
    int result = solution.coinChange2(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864);
    System.out.println(result);
    result = solution.coinChange2(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864);
    System.out.println(result);
  }
}
