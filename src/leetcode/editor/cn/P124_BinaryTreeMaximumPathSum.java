/******************************* Java：二叉树中的最大路径和 *******************************/
// 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
// 该路径 至少包含一个 节点，且不一定经过根节点。
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,3]
// 输出：6
// 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2： 
//
// 输入：root = [-10,9,20,null,null,15,7]
// 输出：42
// 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 提示： 
//
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 1061 👎 0


/******************************* 题目思路 *******************************/
// 本题最大的难点在于，可以从任意节点出发，并且连接路径不仅可以从 “上往下”，还可以 “从下往上” ，“左右” 任意方向连接；
// 所以看到的时候会让人一头雾水。
//
// 任何复杂的题目都是从简单题变形的，我们不妨转换一下思路：
//      “如果题目要求，任意节点到以它为子树的所有子节点的最大路径”
// 这应该是一个很常见的题目，只需要做一个递归：
//      设 maxgain(root) 为求 root 到其叶子节点的最大路径和；
//      那么，所有 null 节点的 maxgain = 0；
//      非空节点的 maxgain = val(root) + max( max( maxgain(left), 0 ）, max( maxgain(right), 0 ) )
//
// 这样，我们相当于求出了 “纵向上，经过该节点的任意两节点间的最长路径”，
// 那么，再引入 “横向” 路径，无非就是遍历所有节点，将与该节点相连的纵向路径连起来，具体做法：
//      maxpathsum(node) = val(node) + max( maxgain(left), 0 ) + max( maxgain(right), 0 )
//
// 所以，本题的核心在于，将问题拆分，分步求解

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P124_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new P124_BinaryTreeMaximumPathSum().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int answer = solution.maxPathSum(root);
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
        int pathmax = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return pathmax;
        }

        private int maxGain(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // 求左右子树纵向的最大得分
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);
            // 引入横向，再求最长路径和
            int node_maxpath = node.val + leftGain + rightGain;

            // 保留全局的 maxpath，一次求出结果
            pathmax = Math.max(pathmax, node_maxpath);

            return node.val + Math.max(leftGain, rightGain);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：：O(n)，树中的每个节点最多访问两次
// 空间复杂度：O(n)，主要取决于递归的深度，最坏情况下（树为单链时），深度为 n