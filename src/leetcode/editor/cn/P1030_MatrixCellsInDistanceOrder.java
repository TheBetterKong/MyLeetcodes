/******************************* Java：距离顺序排列矩阵单元格 *******************************/
// 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
//
// 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。 
//
// 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈
// 顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
//
// 
//
// 示例 1： 
//
// 输入：R = 1, C = 2, r0 = 0, c0 = 0
// 输出：[[0,0],[0,1]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
// 
//
// 示例 2： 
//
// 输入：R = 2, C = 2, r0 = 0, c0 = 1
// 输出：[[0,1],[0,0],[1,1],[1,0]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
// [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
// 
//
// 示例 3： 
//
// 输入：R = 2, C = 3, r0 = 1, c0 = 2
// 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
// 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
// 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= R <= 100 
// 1 <= C <= 100 
// 0 <= r0 < R 
// 0 <= c0 < C 
// 
// Related Topics 排序 
// 👍 45 👎 0


/******************************* 题目思路 *******************************/
// 本题有点类型，树的广度优先搜索算法，一层一层（距离）来输出各个点。
// 那么，问题就是如何找到这个一层一层的关系。（可以直接画图找规律）。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P1030_MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        Solution solution = new P1030_MatrixCellsInDistanceOrder().new Solution();
        // TO TEST
        int R = 2, C = 3, r0 = 1, c0 = 2;
        int[][] answer = solution.allCellsDistOrder(R, C, r0, c0);
        System.out.println(Arrays.deepToString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int[][] result = new int[R*C][2];

            // 初始化，本身一定是第一个点
            result[0][0] = r0;
            result[0][1] = c0;

            int row = r0;
            int col = c0;
            int count = 1; // 计数是否已经遍历完所有节点，总共应该计 R*C 次，点（r0,c0）已经放入
            int[] dr = {1, 1, -1, -1};
            int[] dc = {1, -1, -1, 1};
            // 便于一会利用 i 控制四个维度的绘图
            //    i = 0:  (dr[0],dc[0])：右上的向量
            //    i = 1:  (dr[1],dc[1])：左上的向量
            //    i = 2:  (dr[2],dc[2])：左下的向量
            //    i = 3:  (dr[3],dc[3])：右下的向量
            // 它们共同组成了一层（逆时针矩形闭环）

            while (count < R*C) {
                row --;  // 控制一层一层向外扩张
                for (int i = 0; i < 4; i++) {   // 每一层有 4 条边
                    while ((i % 2 == 0 && row != r0) || (i % 2 != 0 && col != c0)) {
                        // i % 2 = 0：代表右上、左下两个方向绘图，当行 row 回到初始值 r0 时，代表该边绘制完毕
                        // i % 2 = 1：代表左上、右下两个方向绘图，当列 col 回到初始值 c0 时，代表该边绘制完毕
                        if (row >= 0 && row < R && col >= 0 && col < C) {   // 不超过矩阵范围的点，才放入结果里
                            result[count][0] = row;
                            result[count][1] = col;
                            count ++;
                        }
                        row += dr[i];
                        col += dc[i];
                    }
                }
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：
//      我们假定，给的点 (r0, c0) 距离矩阵四个角，横纵坐标差值的绝对值之和最大为 n。
//      那么我们需要遍历的点的数就是 1 + 4 *（1 + 2+ ... + n）= 2n(n+1)
//      因此，时间复杂度为 O(n^2)
// 空间复杂度：O(1)