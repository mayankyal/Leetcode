package com.fishercoder.solutions;

import com.fishercoder.common.classes.TreeNode;

/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
 but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

 Example 1:
 Input:
   1
  / \
 2   3
 Output: 2

 Explanation: The longest consecutive path is [1, 2] or [2, 1].
 Example 2:
 Input:
   2
  / \
 1   3
 Output: 3
 Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class _549 {

    int max = 0;

    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return max;
    }

    private int[] longestPath(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int increasing = 1;
        int decreasing = 1;
        if (root.left != null) {
            int[] left = longestPath(root.left);
            if (root.val == root.left.val + 1) decreasing = left[1] + 1;
            else if (root.val == root.left.val - 1) increasing = left[0] + 1;
        }

        if (root.right != null) {
            int[] right = longestPath(root.right);
            if (root.val == root.right.val + 1) decreasing = Math.max(right[1] + 1, decreasing);
            else if (root.val == root.right.val - 1) increasing = Math.max(right[0] + 1, increasing);
        }

        max = Math.max(max, decreasing + increasing - 1);
        return new int[]{increasing, decreasing};
    }

}
