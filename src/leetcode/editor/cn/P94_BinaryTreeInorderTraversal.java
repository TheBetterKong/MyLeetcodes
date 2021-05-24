/******************************* Java：二叉树的中序遍历 *******************************/
// 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
//
// 示例 1： 
//
// 输入：root = [1,null,2,3]
// 输出：[1,3,2]
// 
//
// 示例 2： 
//
// 输入：root = []
// 输出：[]
// 
//
// 示例 3： 
//
// 输入：root = [1]
// 输出：[1]
// 
//
// 示例 4： 
//
// 输入：root = [1,2]
// 输出：[2,1]
// 
//
// 示例 5： 
//
// 输入：root = [1,null,2]
// 输出：[1,2]
// 
//
// 提示： 
//
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 961 👎 0


/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class P94_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P94_BinaryTreeInorderTraversal().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.right.right = new TreeNode(2);
        List<Integer> answer = solution.inorderTraversal(root);
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
//            inorder_rec(root, res);
            inorder_stack(root, res);
            return res;
        }

        private void inorder_rec(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            inorder_rec(root.left, res);
            res.add(root.val);
            inorder_rec(root.right, res);
        }

        private void inorder_stack(TreeNode root, List<Integer> res) {
            Stack<TreeNode> stk = new Stack<TreeNode>();
            while (root != null || !stk.isEmpty()) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                res.add(root.val);
                root = root.right;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，二叉树的每一个节点都会被访问且只会访问一次
// 空间复杂度：O(n)，递归方法中，取决于递归的深度，迭代方法中取决于栈的大小，其实它们都是受树深的影响，最坏情况下树为一条单链，树深为 n。