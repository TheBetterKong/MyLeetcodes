/******************************* Java：打家劫舍 III *******************************/
// 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之
// 为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方
// 的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
//
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
// 输出: 7
// 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
//
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//    3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
// 输出: 9
// 解释:小偷一晚能够盗取的最高金额= 4 + 5 = 9.
//
//
//
// Related Topics 树 深度优先搜索 动态规划 
// 👍 883 👎 0


/******************************* 题目思路 *******************************/
// T198、213、337 打家劫舍的三大典型例题
//
// 同另外两题的主体思路类似，同样考虑当前节点 “被选中” 和 “不选中” 两种情况：
//  首先，令 g_node(x) 表示，x 被选中的情况下，x 的子树被选择节点的最大权益和
//          n_node(x) 表示，x 不被选中的情况下，x 的子树上被选择节点的最大权益和
//
// 考虑 g_node(x)，x 的左右子节点都不能再被选中，此时
//      g_node(x) = x.val + p_node(l) + p_node(r)
//
// 考虑 p_node(x)，x 的左右子节点都是可选或者不可选状态，那我们直接去最值：
//      p_node(x) = max{g_node(l), p_node(l)} + max{g_node(r), p_node(r)}
//
// 这样，利用递归动态归回，我们就能求解，边界状态为：空节点，返回 0

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.HashMap;
import java.util.Map;

class P337_HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new P337_HouseRobberIii().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        int answer = solution.rob(root);
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
        Map<TreeNode, Integer> g_node = new HashMap<>();
        Map<TreeNode, Integer> p_node = new HashMap<>();

        public int rob(TreeNode root) {
            dfs(root);
            return Math.max(p_node.getOrDefault(root, 0), g_node.getOrDefault(root, 0));
        }

        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            dfs(node.left);
            dfs(node.right);
            g_node.put(node,
                    node.val + p_node.getOrDefault(node.left, 0) + p_node.getOrDefault(node.right, 0));
            p_node.put(node,
                    Math.max(g_node.getOrDefault(node.left, 0), p_node.getOrDefault(node.left, 0))
                            + Math.max(g_node.getOrDefault(node.right, 0), p_node.getOrDefault(node.right, 0)));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，相当于对树做了一次后序遍历
// 空间复杂度：O(n)，主要来源于 递归的深度 和 hashmap 的大小，其中，我们观察到遍历到任意节点时，我们都只需要使用 g_node(l)、g_node(r)、p_node(l)、p_node(r)
//              因此，每次递归时，可以只保存这四个变量的值可以对空间复杂度进行优化，但是受递归影响还是会达到 O(n)