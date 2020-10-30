/******************************* Java：二叉搜索树的最近公共祖先 *******************************/
// 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
// 一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
// 输出: 6
// 解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
// 输出: 2
// 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
// Related Topics 树 
// 👍 480 👎 0


/******************************* 题目思路 *******************************/
// 本题告诉了是在一个二叉搜索树中进行，那么简单了很多：
//      如果 p q 都小于当前节点，那么它们一定在当前节点的左子树上，当前节点取左子树的节点；
//      如果 p q 都大于当前节点，那么它们一定在当前节点的右子树上，当前节点取右子树的节点；
//      如果 p q 一个大于当前节点，一个小于，那么它们一定分布在当前节点两侧，那么当前节点肯定就是它们的公共节点；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P235_LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P235_LowestCommonAncestorOfABinarySearchTree().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        TreeNode answer = solution.lowestCommonAncestor(root, p, q);
        System.out.println(answer.val);
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
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode node = root;
            while (true) {
                if (p.val < node.val && q.val < node.val) {
                    node = node.left;
                } else if (p.val > node.val && q.val > node.val) {
                    node = node.right;
                } else {
                    break;
                }
            }
            return node;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂来源于算法里的循环，而循环的次数相当于深度遍历，与树的深度有关，满二叉树的时间复杂度为 O(log n)，最坏情况下（树退化为链表）为 O(n)
// 空间复杂度 O(1)