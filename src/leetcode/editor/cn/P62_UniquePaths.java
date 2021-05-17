/******************************* Java：不同路径 *******************************/
// 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 3, n = 7
// 输出：28
//
// 示例 2： 
//
// 输入：m = 3, n = 2
// 输出：3
// 解释：
// 从左上角开始，总共有 3 条路径可以到达右下角。
// 1. 向右 -> 向下 -> 向下
// 2. 向下 -> 向下 -> 向右
// 3. 向下 -> 向右 -> 向下
// 
//
// 示例 3： 
//
// 输入：m = 7, n = 3
// 输出：28
// 
//
// 示例 4： 
//
// 输入：m = 3, n = 3
// 输出：6
//
// 
//
// 提示： 
//
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数组 动态规划 
// 👍 990 👎 0


/******************************* 题目思路 *******************************/
// 本题是一个简单的排列组合问题
// 以 m = 7, n = 3，为例，机器人要走到右下角，总计需要向右走 6 步，向下走 2 两步，共 8 步
// 接下来就是，什么时候向右什么时候向下，有多少种情况：C_8^2 = 8 x 7 / 2 = 28 种

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P62_UniquePaths {
    public static void main(String[] args) {
        Solution solution = new P62_UniquePaths().new Solution();
        // TO TEST
        int m = 3, n = 7;
        int answer = solution.uniquePaths(m, n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePaths(int m, int n) {
            long res = 1;
            for (int x = n, y = 1; y < m; x++, y++) {
                res = res * x / y;
            }
            return (int)res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(min(m, n))
// 空间复杂度：O(1)