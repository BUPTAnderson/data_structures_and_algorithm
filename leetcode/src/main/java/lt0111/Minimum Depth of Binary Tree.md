### Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.  
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.  
**Note:** A leaf is a node with no children.

<br>

#### Example 1:
![](../../resources/lt0111.png)
<pre>
<strong>Input:</strong>root = [3,9,20,null,null,15,7]
<strong>Output:</strong> 2
</pre>
#### Example 2:
<pre>
<strong>Input:</strong> root = [2,null,3,null,4,null,5,null,6]
<strong>Output:</strong> 5
</pre>

<br>

#### Constraints:

- The number of nodes in the tree is in the range <code>[0, 10<sup>5</sup>]</code>.
- `-1000 <= Node.val <= 1000`