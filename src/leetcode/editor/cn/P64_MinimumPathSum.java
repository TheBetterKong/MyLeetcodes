/******************************* Java：最小路径和 *******************************/
// 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
// 输出：7
// 解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 输入：grid = [[1,2,3],[4,5,6]]
// 输出：12
// 
//
// 提示： 
//
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 878 👎 0


/******************************* 题目思路 *******************************/
// 一开始把题目想复杂了，忘了说明里只能向下或向右移动，所以去用 Floyd 算法求解，问题复杂化了。
// 其实可以简单的遍历数组实现：
// 维护一个 sum 数组，sum[i][j] 是从 (0,0) 到 (i,j) 为最短路径和
//   ① 当 i>0 且 j=0 时，sum[i][0] = sum[i-1][0] + grid[i][0]
//   ② 当 i=0 且 j>0 时，sum[0][j] = sum[0][j-1] + grid[0][j]
//   ③ 当 i>0 且 j>0 时，sum[i][j] = min( sum[i−1][j], sum[i][j−1] ) + grid[i][j]
// 最终，sum[m-1][n-1] 即为从网格右上角到左下角的最短路径和。
//
// 其实本题可以加大难度：
//  ① 不限制只能向右向左移动
//  ② 不仅要输出最短路径和，还要输出路径


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P64_MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new P64_MinimumPathSum().new Solution();
        // TO TEST
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int answer = solution.minPathSum(grid);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

            int rows = grid.length, cols = grid[0].length;
            int[][] sum = new int[rows][cols];
            sum[0][0] = grid[0][0];

            for (int i = 1; i < rows; i++) {
                sum[i][0] = sum[i-1][0] + grid[i][0];
            }
            for (int i = 1; i < cols; i++) {
                sum[0][i] = sum[0][i-1] + grid[0][i];
            }
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < cols; j++) {
                    sum[i][j] = Math.min(sum[i-1][j], sum[i][j-1]) + grid[i][j];
                }
            }

            return sum[rows - 1][cols - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(mn)
// 空间复杂度：O(mn）,空间复杂度可以优化为只存储上一行的 sum 值