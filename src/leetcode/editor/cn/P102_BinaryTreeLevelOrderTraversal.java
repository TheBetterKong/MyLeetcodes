/******************************* Java：二叉树的层序遍历 *******************************/
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
//
// 示例： 
// 二叉树：[3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
// ]
// 
// Related Topics 树 广度优先搜索 
// 👍 877 👎 0


/******************************* 题目思路 *******************************/
// 树的广度遍历，属于树的基本操作
// 利用队列实现，避免递归，每一层的节点数用一个 size 变量保存

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.*;

class P102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P102_BinaryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> answer = solution.levelOrder(root);
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (root == null) {
                return res;
            }

            // 借助队列实现深度优先搜索
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            // 将第一层节点加入
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>();     // 存放某一层的节点
                int curlevelSize = queue.size();                    // 某一层的节点数
                for (int i = 1; i <= curlevelSize; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                res.add(level);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，树中的每个节点都会进出队一次
// 空间复杂度：O(n)，主要是用来存储节点的队列