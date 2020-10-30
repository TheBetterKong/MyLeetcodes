/******************************* Java：对称二叉树 *******************************/
// 给定一个二叉树，检查它是否是镜像对称的。
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1086 👎 0


/******************************* 题目思路 *******************************/
// 递归思路：比较根节点的左右子节点是否相等，然后对这两个子节点，分别递归调用是否对称的判断
// 迭代思路：
//      利用 队列，将根节点的左右子节点入队，每次提取对头的两个节点，比较是否相等（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像）；
//      然后将两个结点的左右子结点按相反的顺序插入队列中：
//      当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class P101_SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new P101_SymmetricTree().new Solution();
        // TO TEST
        TreeNode tree = new TreeNode(0);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(1);
        tree.left.left = new TreeNode(2);
        tree.right.right = new TreeNode(2);

        boolean answer = solution.isSymmetric(tree);
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;

            TreeNode node1;
            TreeNode node2;
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root.left);
            queue.offer(root.right);

            while (!queue.isEmpty()) {
                node1 = queue.poll();
                node2 = queue.poll();

                if (node1 == null && node2 == null) {
                    continue;
                }
                if ((node1 == null || node2 == null) || (node1.val != node2.val)) {
                    return false;
                }

                // 反向对称入队
                queue.offer(node1.left);
                queue.offer(node2.right);
                queue.offer(node1.right);
                queue.offer(node2.left);
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(n)，队列里存储的节点数 < n