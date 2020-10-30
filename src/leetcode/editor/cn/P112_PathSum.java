/******************************* Java：路径总和 *******************************/
//给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
// 给定如下二叉树，以及目标和 sum = 22，
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \      \
//        7    2      1
// 
//
// 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
// Related Topics 树 深度优先搜索 
// 👍 451 👎 0


/******************************* 题目思路 *******************************/
// 其实也是一个层次遍历的过程，只是在遍历的过程中需要不断的记录根节点到当前节点的路经长
// 这里可以直接利用一个 队列 ，与节点队列对应，它专门用来保存当前路径长

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class P112_PathSum {
    public static void main(String[] args) {
        Solution solution = new P112_PathSum().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int sum = 22;

        boolean answer = solution.hasPathSum(root, sum);
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
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;

            Queue<TreeNode> queNode = new LinkedList();  // 保存节点
            Queue<Integer> queVal = new LinkedList();    // 保存根节点到当前节点的路径长

            queNode.offer(root);
            queVal.offer(root.val);

            while (!queNode.isEmpty()) {
                TreeNode now = queNode.poll();
                int temp = queVal.poll();
                if (now.left == null && now.right == null) {
                    if (temp == sum) {
                        return true;
                    }
                    continue;
                }
                if (now.left != null) {
                    queNode.offer(now.left);
                    queVal.offer(now.left.val + temp);
                }
                if (now.right != null) {
                    queNode.offer(now.right);
                    queVal.offer(now.right.val + temp);
                }
            }
            return false;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......
