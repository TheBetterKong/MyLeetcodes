/******************************* Java：二叉树的锯齿形层序遍历 *******************************/
//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 👍 500 👎 0


/******************************* 题目思路 *******************************/
// 我这里是偶数层做一次 reverse，也可以在 level 时采用双端队列，奇数层往尾插入，偶数层往头插入

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.*;

class P103_BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // TO TEST
        BinaryTree binTree = new BinaryTree();
        List<Integer> nums = new ArrayList<>(Arrays.asList(3,9,20,-1,-1,15,7));
        TreeNode root = binTree.buildTree(nums);
        List<List<Integer>> answer = solution.zigzagLevelOrder(root);
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (root == null) {
                return res;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int flag = 0;

            while (!queue.isEmpty()) {
                flag++;
                List<Integer> level = new ArrayList<>();
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    TreeNode p = queue.poll();
                    level.add(p.val);
                    if (p.left != null) {
                        queue.offer(p.left);
                    }
                    if (p.right != null) {
                        queue.offer(p.right);
                    }
                }
                if (flag % 2 == 0) {
                    Collections.reverse(level);
                }
                res.add(level);
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(nlogn），logn 是每一层需要 reverse 一次，每次 reverse 是 O(n)
// 空间复杂度：O(n），队列的长度