### Convert Sorted Array to Binary Search Tree

Given an integer array `nums` where the elements are sorted in **ascending order**, convert *it to a* ***height-balanced*** *binary search tree.*

A **height-balanced** binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.



#### Example 1:
![](../../resources/lt0108_1.png)
<pre>
<strong>Input:</strong> nums = [-10,-3,0,5,9]
<strong>Output:</strong> [0,-3,9,-10,null,5]
<strong>Explanation:</strong> [0,-10,5,null,-3,null,9] is also accepted:
<img src="/Users/anderson/github/data_structures_and_algorithm/leetcode/src/main/resources/lt0108_2.png" />
</pre>

#### Example 2:
![](../../resources/lt0108_3.png)
<pre>
<strong>Input:</strong> nums = [1,3]
<strong>Output:</strong> [3,1]
<strong>Explanation:</strong> [1,3] and [3,1] are both a height-balanced BSTs.
</pre>

#### Constraints:

- <code>1 <= nums.length <= 10<sup>4</code>
- <code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</code>
- `nums` is sorted in a **strictly increasing** order.