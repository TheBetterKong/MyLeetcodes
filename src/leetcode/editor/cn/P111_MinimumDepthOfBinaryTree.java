/******************************* Java：二叉树的最小深度 *******************************/
// 给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
// 输入：root = [3,9,20,null,null,15,7]
// 输出：2
// 
//
// 示例 2： 
//
// 
// 输入：root = [2,null,3,null,4,null,5,null,6]
// 输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 390 👎 0


/******************************* 题目思路 *******************************/
// 在层次遍历的基础上，加上一个判断，如果某个节点同时没有左右子树，则直接返回当前的层次深度即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.LinkedList;
import java.util.Queue;

class P111_MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P111_MinimumDepthOfBinaryTree().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(2);
        root.right.left = new TreeNode(2);

        int answer = solution.minDepth(root);
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
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList();
            int depth = 1;
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeNode p = queue.poll();
                    if (p.left == null && p.right == null){
                        return depth;
                    }
                    if (p.left != null) {
                        queue.offer(p.left);
                    }
                    if (p.right != null) {
                        queue.offer(p.right);
                    }
                    size--;
                }
                depth++;
            }
            return depth;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，取决于树形结构
