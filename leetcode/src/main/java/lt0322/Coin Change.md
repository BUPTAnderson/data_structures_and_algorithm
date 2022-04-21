### 322. Coin Change
You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.

Return *the fewest number of coins that you need to make up that amount*. If that amount of money cannot be made up by any combination of the coins, return `-1`.

You may assume that you have an infinite number of each kind of coin.



#### Example 1:
<pre>
<strong>Input:</strong> coins = [1,2,5], amount = 11
<strong>Output:</strong> 3
<strong>Explanation:</strong> 11 = 5 + 5 + 1
</pre>
#### Example 2:
<pre>
<strong>Input:</strong> coins = [2], amount = 3
<strong>Output:</strong> -1
</pre>
#### Example 3:
<pre>
<strong>Input:</strong> coins = [1], amount = 0
<strong>Output:</strong> 0
</pre>
#### Example 4:
<pre>
<strong>Input:</strong> coins = [1], amount = 1
<strong>Output:</strong> 1
</pre>
#### Example 5:
<pre>
<strong>Input:</strong> coins = [1], amount = 2
<strong>Output:</strong> 2
</pre>

#### Constraints:

- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 231 - 1`
- `0 <= amount <= 104`