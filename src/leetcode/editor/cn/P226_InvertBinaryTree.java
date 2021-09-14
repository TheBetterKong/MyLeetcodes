/******************************* Java：翻转二叉树 *******************************/
// 翻转一棵二叉树。
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
// 这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 
// 👍 669 👎 0


/******************************* 题目思路 *******************************/
// 递归翻转

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P226_InvertBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P226_InvertBinaryTree().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(1);

        TreeNode answer = solution.invertTree(root);
        System.out.println(answer.val);
        System.out.println(answer.left.val);
        System.out.println(answer.left.left.val);
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
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            // 翻转左右子树
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            // 完成根节点的翻转
            root.left = right;
            root.right = left;
            return root;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 因为会对每个节点都实现节点的左右字树的翻转，所以时间复杂度为 O(n)
// 递归栈的最大深度为，二叉树的高度，因此空间复杂度为 O(log n)，但是最坏情况下（树里每个节点都只有一个子节点），空间复杂度为 O(n)