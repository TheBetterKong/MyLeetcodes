/******************************* Java：验证二叉搜索树 *******************************/
// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
// 输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
// 输出: false
// 解释: 输入为: [5,1,4,null,null,3,6]。
//    根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 1071 👎 0


/******************************* 题目思路 *******************************/
// 本题两种思路：
//  （1）按照递归判断的方法，递归判断；
//  （2）二叉搜索树的中序遍历就是，数从小到大排列，按照这种思路，中序遍历二叉树即可；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class P98_ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P98_ValidateBinarySearchTree().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        boolean answer = solution.isValidBST(root);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        public boolean isValidBST(TreeNode root) {
            boolean res = isValidBST_inOrder(root);
            return res;
        }

        // 方法一：递归算法
        private boolean isValidBST_rec(TreeNode root) {
            return rec_helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean rec_helper(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return rec_helper(node.left, lower, node.val) && rec_helper(node.right, node.val, upper);
        }

        // 方法二：中序遍历算法
        private boolean isValidBST_inOrder(TreeNode root) {
            Deque<TreeNode> stk = new LinkedList<TreeNode>();
            long last_inorder = Long.MIN_VALUE;

            while (!stk.isEmpty() || root != null) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                if (root.val <= last_inorder) {
                    return false;
                }
                last_inorder = root.val;
                root = root.right;
            }
            return true;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，二叉树的每一个节点都会被访问且只会访问一次
// 空间复杂度：O(n)，递归方法中，取决于递归的深度，迭代方法中取决于栈的大小，其实它们都是受树深的影响，最坏情况下树为一条单链，树深为 n。