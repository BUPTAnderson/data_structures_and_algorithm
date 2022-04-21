### Kth Smallest Element in a BST
Given the `root` of a binary search tree, and an integer `k`, return the <code>k<sup>th</sup></code> (**1-indexed**) *smallest element in the tree.*  

<br>

#### Example 1:
![](../../resources/lt0230_1.png)
<pre>
<strong>Input:</strong> root = [3,1,4,null,2], k = 1
<strong>Output:</strong> 1
</pre>
#### Example 2:
![](../../resources/lt0230_2.png)
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,null,1], k = 3
<strong>Output:</strong> 3
</pre>

#### Constraints:

- The number of nodes in the tree is `n`.
- <code>1 <= k <= n <= 10<sup>4</sup></code>
- <code>0 <= Node.val <= 10<sup>4</sup></code>


**Follow up:** If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?