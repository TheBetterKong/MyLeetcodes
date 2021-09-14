/******************************* Java：螺旋矩阵 *******************************/
// 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
// 输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 提示： 
//
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 850 👎 0


/******************************* 题目思路 *******************************/
// 规律：
//    按圈遍历，每次循环遍历一层
//      circle 由 row 和 col 的较小者决定，为：circle = (row<col?row:col)/2；
//      当 row 和 col 的较小者为偶数时，按一层层的 circle 遍历完矩阵即可；
//      当 row 和 col 的较小者为奇数时，按一层层的 circle 遍历完矩阵后，矩阵中间会残留有小行或者小列（由矩阵的形状决定）没有遍历到的情况；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class P54_SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new P54_SpiralMatrix().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            int row = matrix.length;
            int col = matrix[0].length;

            int circle = Math.min(row, col) / 2;
            // 由外往里
            for (int ci = 0; ci < circle; ci++) {
                // 从左往右
                for (int i = ci; i < col - ci; i++) {
                    res.add(matrix[ci][i]);
                }
                // 从上往下
                for (int j = ci + 1; j < row - ci; j++) {
                    res.add(matrix[j][col - ci - 1]);
                }
                // 从右往左
                for (int m = col - ci - 2; m >= ci; m--) {
                    res.add(matrix[row - ci -1][m]);
                }
                // 从下往上
                for (int n = row - ci -2; n > ci; n--) {
                    res.add(matrix[n][ci]);
                }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(mn)，每个元素遍历一次
// 空间复杂度：O(1)