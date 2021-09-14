/******************************* Java：岛屿数量 *******************************/
// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
//
// 示例 1： 
//
// 输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
// ]
// 输出：1
// 
//
// 示例 2： 
//
// 输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
// ]
// 输出：3
// 
//
//
// 提示： 
//
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1292 👎 0


/******************************* 题目思路 *******************************/
// 方案一：基于深度扩张算法，dfs 实现
// 方案二：基于广度扩张算法，队列实现
// 方案三：并查集，见题解

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;
import leetcode.editor.cn.myDataStructure.*;

class P200_NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200_NumberOfIslands().new Solution();
        // TO TEST
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int answer = solution.numIslands(grid);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int rows = grid.length;
            int cols = grid[0].length;
            int num_islands = 0;

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (grid[r][c] == '1') {
                        num_islands++;
                        dfs(r, c, grid);
                    }
                }
            }

            return num_islands;
        }

        public void dfs(int i, int j, char[][] grid) {
            int rows = grid.length;
            int col = grid[0].length;

            if (i < 0 || j < 0 || i >= rows || j >= col || grid[i][j] == '0') {
                return;
            }

            grid[i][j] = '0';
            dfs(i - 1, j, grid);
            dfs(i + 1, j, grid);
            dfs(i, j - 1, grid);
            dfs(i, j + 1, grid);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(mn)，全部遍历一次
// 空间复杂度：O(mn)，来源于递归的深度，最坏情况下，网络全为陆地；