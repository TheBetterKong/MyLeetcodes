/******************************* Java：从前序与中序遍历序列构造二叉树 *******************************/
// 根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意: 
// 你可以假设树中没有重复的元素。
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
// 中序遍历 inorder  = [9,3,15,20,7]
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 1051 👎 0


/******************************* 题目思路 *******************************/
// 首先，本题最直观的方法就是，递归。递归的思路比较传统，我们重点关注迭代方法；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // TO TEST
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);

        // 构造为二叉树，并层次遍历展示树的样子
        BinaryTree rootTree = new BinaryTree(root);
        rootTree.levelOrder(rootTree.root);
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
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 递归实现：
            //      题目假设没有重复元素，可以借助 hash 来在数组里定位节点位置
            for (int k = 0; k < inorder.length; k++) {
                map.put(inorder[k], k);
            }
            TreeNode res = buildTreeRec(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

            // 迭代实现：
            // TreeNode res = buildTreeIter(preorder, inorder);

            return res;
        }

        /**
         * @Author TheBetterKong
         * @Description 递归实现二叉树的构造
         * @Date 17:46 2021/5/27
         * @Param 
         * @param preorder  前序遍历的集合
         * @param inorder   中序遍历的集合
         * @param preLeft   左子树前序遍历集合的开始位置
         * @param preRight  右子树前序遍历集合的结束位置
         * @param inLeft    左子树中序遍历集合的开始位置
         * @param inRight   右子树中序遍历集合的结束位置
         * @return leetcode.editor.cn.TreeNode
         */
        private TreeNode buildTreeRec(int[] preorder, int[] inorder,
                                      int preLeft, int preRight,
                                      int inLeft, int inRight) {
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[preLeft]);    // 前序遍历的第一个节点就是根节点
            int inorderIdxRoot = map.get(root.val);             // 在中序里定位到这个节点，从而将 preorder 和 inorder 分为左右子节点两段

            int countLeftNodes = inorderIdxRoot - inLeft;
            int countRightNodes = inRight - inorderIdxRoot;
            // 将中序集合分为左右子节点的集合，然后往下递归
            root.left = buildTreeRec(preorder, inorder,
                    preLeft + 1, preLeft + countLeftNodes,
                    inLeft, inorderIdxRoot - 1);
            // 将前序集合分为左右子节点的集合，然后往下递归
            root.right = buildTreeRec(preorder, inorder,
                    preLeft + countLeftNodes + 1, preRight,
                    inorderIdxRoot + 1, inRight);

            return root;
        }

        /**
         * @Author TheBetterKong
         * @Description 迭代法实现二叉树的构造
         * @Date 18:45 2021/5/27
         * @Param
         * @param preorder  前序遍历的集合
         * @param inorder   中序遍历的集合
         * @return leetcode.editor.cn.TreeNode
         */
        private TreeNode buildTreeIter(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stk = new LinkedList<>();
            stk.push(root);

            int inorderIdx = 0;
            for (int i = 1; i < preorder.length; i++) {
                TreeNode node = stk.peek();
                if (node.val != inorder[inorderIdx]) {
                    node.left = new TreeNode(preorder[i]);
                    stk.push(node.left);
                } else {
                    while (!stk.isEmpty() && stk.peek().val == inorder[inorderIdx]) {
                        node = stk.pop();
                        inorderIdx++;
                    }
                    node.right = new TreeNode(preorder[i]);
                    stk.push(node.right);
                }
            }

            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 两种方法是复杂度相同，都为：
// 时间复杂度：O(n)
// 空间复杂度：O(n)
//      递归方法中，取决于递归的深度，另外构建的 hash 需要 O(n) 的复杂度
//      迭代方法中取决于栈的大小，其实它们都是受树深的影响，最坏情况下树为一条单链，树深为 n。