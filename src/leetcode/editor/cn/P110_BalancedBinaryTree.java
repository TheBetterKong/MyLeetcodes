/******************************* Java：平衡二叉树 *******************************/
// 给定一个二叉树，判断它是否是高度平衡的二叉树。
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
// 输入：root = [3,9,20,null,null,15,7]
// 输出：true
// 
//
// 示例 2： 
//
// 
// 输入：root = [1,2,2,3,3,null,null,4,4]
// 输出：false
// 
//
// 示例 3： 
//
// 
// 输入：root = []
// 输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 
// 👍 510 👎 0


/******************************* 题目思路 *******************************/
// 这题最明显的思路，就是直接 至顶向下 的递归调用，不断判断左右字树的高度，然后判断是否为平衡二叉树，这里直接扒一下官网代码，
//      觉得比我的写的直接很多：
//        class Solution {
//            public boolean isBalanced(TreeNode root) {
//                if (root == null) {
//                    return true;
//                } else {
//                    return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//                }
//            }
//
//            public int height(TreeNode root) {
//                if (root == null) {
//                    return 0;
//                } else {
//                    return Math.max(height(root.left), height(root.right)) + 1;
//                }
//            }
//        }
//      但是这样的，时间复杂度为 O(n^2)，效率太低了。
// 在上述方法中，在判断某个节点是否平衡时，会将它的所有的子节点都调用一遍 height 函数从而带来了很大的时间浪费。
// 官方提供了一种，自底向上的 递归调用方法：
//      类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
//      如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1。
//      如果存在一棵子树不平衡，则整个二叉树一定不平衡。


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P110_BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P110_BalancedBinaryTree().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        boolean answer = solution.isBalanced(root);
        System.out.print(answer);
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
        public boolean isBalanced(TreeNode root) {
            return balanced(root) != -1;
        }

        private int balanced(TreeNode node) {
            if (node == null) return 0;
            int leftHeight, rightHeight;
            // 如果左子树已经返回 -1 了就不需要再递归右子树了，直接返回 -1
            if ((leftHeight = balanced(node.left)) == -1
                    || (rightHeight = balanced(node.right)) == -1
                    || Math.abs(leftHeight - rightHeight) > 1)
                return -1;
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 退化为 O(n)