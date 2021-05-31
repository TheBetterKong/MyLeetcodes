/******************************* Java：二叉树展开为链表 *******************************/
// 给你二叉树的根结点 root ，请你将它展开为一个单链表：
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,5,3,4,null,6]
// 输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 输入：root = []
// 输出：[]
// 
//
// 示例 3：
// 
// 输入：root = [0]
// 输出：[0]
// 
//
//
// 提示： 
//
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 树 深度优先搜索 
// 👍 811 👎 0


/******************************* 题目思路 *******************************/
// 题目要求在 O(1) 的空间复杂度下，按照 前序遍历 的顺序展开树，所以我们不能再像之前那样利用 递归 或者 栈 实现前序遍历
//
// 按照前序遍历访问顺序：根、左子、右子。
// 那么，对于任意一个节点，如果其左子节点为空，那它不需要进行展开；若其左子节点不为空，则其左子节点的最后一个节点被访问后，开始访问其右子节点。
// 而左子树里最后一个被访问的节点，就是左子树里最右边的节点。
// 所以，确定前序遍历的关系，就是不断的将右子节点，挂载至其左子树的最右边的节点。
//
// 具体做法就是：
//      对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
//      然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。
//      对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P114_FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new P114_FlattenBinaryTreeToLinkedList().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        solution.flatten(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
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
        public void flatten(TreeNode root) {
            TreeNode cur = root;
            while (cur != null) {
                if (cur.left != null) {
                    // 找前驱，左子树最右边的节点
                    TreeNode predecessor = cur.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    // 挂载
                    predecessor.right = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                }
                cur = cur.right;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，在寻找前驱节点的过程中，每个节点最多被额外访问一次。
// 空间复杂度：O(1)。