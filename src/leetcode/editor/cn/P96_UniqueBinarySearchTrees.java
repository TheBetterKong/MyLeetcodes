/******************************* Java：不同的二叉搜索树 *******************************/
// 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
// 示例 1： 
//
// 输入：n = 3
// 输出：5
// 
//
// 示例 2： 
//
// 输入：n = 1
// 输出：1
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 动态规划 
// 👍 1161 👎 0


/******************************* 题目思路 *******************************/
// 本题是一个动态规划的题目，其实写几组数，自己构造一下二叉搜索树就能发现规律
// 我们令 f(n) 表示：由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 的种数；
//
// 现在我们假设，二叉搜索树以 节点 k 作为根节点，那根节点的左子节点共有 k-1 个数，右子节点有 n-k 个数，
// 那么左子树的组合情况就是 f(k-1)，右子树为 f(n-k)；
//
// 现在求 f(n) 就相当于遍历所有 1 ~ n 这 n 个数作为根节点的情况，最终求和即可；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new P96_UniqueBinarySearchTrees().new Solution();
        // TO TEST
        int n = 6;
        int answer = solution.numTrees(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numTrees(int n) {
            if (n == 0) return 0;
            int[] res = new int[n+1];

            // 计算每一个节点数，可能组成的二叉搜索树的组合情况
            res[0] = 1;
            res[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    res[i] += res[j - 1] * res[i - j];
                }
            }
            return res[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n^2)，可利用 “卡塔兰数”：c0=1, c_{n+1} = \frac{2(2n+1)}{n+2} c_n，将时间复杂度降为 O(n)
// 空间复杂度：O(n)