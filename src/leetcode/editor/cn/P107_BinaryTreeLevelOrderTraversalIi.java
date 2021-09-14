/******************************* Java：二叉树的层次遍历 II *******************************/
// 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如： 
// 给定二叉树 [3,9,20,null,null,15,7],
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层次遍历为： 
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 354 👎 0


/******************************* 题目思路 *******************************/
// 也是一个层次遍历的反向应用，对比 T102

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.*;

class P107_BinaryTreeLevelOrderTraversalIi {
    public static void main(String[] args) {
        Solution solution = new P107_BinaryTreeLevelOrderTraversalIi().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> answer = solution.levelOrderBottom(root);

        System.out.println("{");
        for (List level : answer) {
            System.out.print("[");
            for (Object i : level) {
                System.out.print(i + ",");
            }
            System.out.println("],");
        }
        System.out.println("}");
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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> res = new LinkedList<List<Integer>>(); // 最后的结果队列
            if (root == null) return res;

            Queue<TreeNode> queue = new LinkedList<TreeNode>(); // 保存中间结果，当前层的节点队列，用来遍历下一层
            queue.offer(root);

            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>(); // 保存每一层的节点
                int size = queue.size();

                // 从当前层遍历下一层
                for (int i = 0; i < size; i++) {
                    TreeNode p = queue.poll();
                    level.add(p.val);

                    if (p.left != null) queue.offer(p.left);
                    if (p.right != null) queue.offer(p.right);
                }

                res.add(0, level);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，相当于每个节点都需要访问一次，而每一层节点访问完毕，再向结果队列头部添加链表的时间复杂度 o(1)
// 空间复杂度 O(n)，共需要构造三个队列，但是队列里的节点数都不会超过 n