You are given an integer array `nums` and you have to return a new `counts` array. The counts array has the property where `counts[i]` is the number of smaller elements to the right of `nums[i]`.


####Example 1:

<pre xmlns="http://www.w3.org/1999/html">
<strong>Input:</strong> nums = [5,2,6,1]
<strong>Output:</strong> [2,1,1,0]
<strong>Explanation:</strong>
To the right of 5 there are <strong>2</strong> smaller elements (2 and 1).
To the right of 2 there is only <strong>1</strong> smaller element (1).
To the right of 6 there is <strong>1</strong> smaller element (1).
To the right of 1 there is <strong>0</strong> smaller element.
</pre>

####Example 2:
<pre>
<strong>Input:</strong> nums = [-1]
<strong>Output:</strong> [0]
</pre>

####Example 3:
<pre>
<strong>Input:</strong> nums = [-1,-1]
<strong>Output:</strong> [0,0]
</pre>

####Constraints:

- <code>1 <= nums.length <= 10<sup>5</sup></code>
- <code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup><code>