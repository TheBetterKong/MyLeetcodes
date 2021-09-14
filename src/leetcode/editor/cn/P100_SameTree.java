/******************************* Java：相同的树 *******************************/
// 给定两个二叉树，编写一个函数来检验它们是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
// 输出: true
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
// 输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
// 输出: false
// 
// Related Topics 树 深度优先搜索 
// 👍 492 👎 0


/******************************* 题目思路 *******************************/
// 最简单的方法就是，利用递归的思想，相当于深度优先搜索，先比较两个根节点是否相等，然后递归比较他们的左右字树是否相等；
// 递归也应该是树里最常用的思想：
//      但是递归对程序员来说，修养要求极高，容易出现：无穷递归、栈溢出等各种问题，安全性也比较难把握，所以我们应该尽量避免递归的使用。
// 所以也需要尽量的采用 栈、队列 来实现。
// 这里，利用队列比较，实现类似广度优先搜索的算法。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.LinkedList;
import java.util.Queue;

class P100_SameTree {
    public static void main(String[] args) {
        Solution solution = new P100_SameTree().new Solution();
        // TO TEST
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        //q.right = new TreeNode(3);

        boolean answer = solution.isSameTree(q,p);
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
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // 初始判断
            if (p == null && q == null) {
                return true;
            }
            else if (p == null || q == null) {
                return false;
            }
            // 正式比较
            Queue<TreeNode> pqueue = new LinkedList<TreeNode>();
            Queue<TreeNode> qqueue = new LinkedList<TreeNode>();
            pqueue.offer(p);
            qqueue.offer(q);

            while (!pqueue.isEmpty() && !qqueue.isEmpty()) {
                TreeNode node1 = pqueue.poll();
                TreeNode node2 = qqueue.poll();

                if (node1.val != node2.val) return false;

                TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;

                // 不相同
                if (left1 == null ^ left2 == null) {
                    return false;
                }
                if (right1 == null ^ right2 == null) {
                    return false;
                }
                // 节点不为空就加入队列
                if (left1 != null) {
                    pqueue.offer(left1);
                }
                if (right1 != null) {
                    pqueue.offer(right1);
                }
                if (left2 != null) {
                    qqueue.offer(left2);
                }
                if (right2 != null) {
                    qqueue.offer(right2);
                }
            }
            return pqueue.isEmpty() && qqueue.isEmpty(); // 都为空，说明比较完了，有一个不为空说明两棵树不相同
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度喝空间复杂度都为 O(min(m,n))，m，n 为两棵二叉树的节点数