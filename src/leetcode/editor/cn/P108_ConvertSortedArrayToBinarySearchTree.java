/******************************* Java：将有序数组转换为二叉搜索树 *******************************/
//将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定有序数组: [-10,-3,0,5,9],
//
//一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 树 深度优先搜索 
// 👍 625 👎 0


/******************************* 题目思路 *******************************/
// 关键在于怎样选择这棵平衡二叉树的根节点，
// 由于数组已经排好序，那么接下来就直接在 左子数组 和 右子数组 递归构建即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P108_ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P108_ConvertSortedArrayToBinarySearchTree().new Solution();
        // TO TEST
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = solution.sortedArrayToBST(nums);
        System.out.print(root.val);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length-1);
        }

        private TreeNode helper (int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }

            int mid = (start + end) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, start, mid - 1);
            root.right = helper(nums, mid + 1, end);
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 递归栈的深度为 O(log n)，数组的每一个元素都会被访问一次，时间复杂度 O(n)