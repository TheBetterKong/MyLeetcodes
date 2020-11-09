/******************************* Java：二叉树的最大深度 *******************************/
// 给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
// 给定二叉树 [3,9,20,null,null,15,7]，
//
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 
// 👍 728 👎 0


/******************************* 题目思路 *******************************/
// 递归思路：直接根节点 左子树 和 右字树 的深度的更大值+1
// 迭代思路：
//      相当于层次遍历，利用队列，然后采用一个变量 depth 来记录层数（即最后的深度）

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class P104_MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P104_MaximumDepthOfBinaryTree().new Solution();
        // TO TEST
        TreeNode tree = new TreeNode(0);
        tree.left = new TreeNode(1);
        tree.left.left = new TreeNode(2);
        int answer = solution.maxDepth(tree);
        System.out.println(answer);
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
        public int maxDepth(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();

            if (root == null) {
                return 0;
            }

            int depth = 0;  // 记录最大深度
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                // 遍历每层，并将下一层的节点入队
                while (size > 0) {
                    TreeNode p = queue.poll();
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
// 时间复杂度 O(n)，相当于会将树里的所有节点都遍历一次
// 空间复杂度：取决于树里，节点最多的层