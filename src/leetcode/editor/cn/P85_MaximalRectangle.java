/******************************* Java：最大矩形 *******************************/
// 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
// 示例 1： 
//
// 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
// 输出：6
// 解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 输入：matrix = []
// 输出：0
// 
//
// 示例 3： 
//
// 输入：matrix = [["0"]]
// 输出：0
// 
//
// 示例 4： 
//
// 输入：matrix = [["1"]]
// 输出：1
// 
//
// 示例 5： 
//
// 输入：matrix = [["0","0"]]
// 输出：0
//
//
// 提示： 
//
// rows == matrix.length 
// cols == matrix[0].length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 哈希表 动态规划 
// 👍 927 👎 0


/******************************* 题目思路 *******************************/
// 同样按照之前 T42、T84 总结的办法，先从横纵两个角度思考暴力方法（横向往往更难优化，所以这里直接纵向考虑）
//
// 横向遍历的思路是：确定高，然后寻找矩形的最大宽度。
// 前面 T42、T84 给出的是一维数组，所以直接用每个元素值定为高；
// 但是这里是二维矩阵了，矩阵的高不再是元素值，而是某一列连续 1 的个数，因此纵向也需要加入一次遍历；
//
// 现在我们确定遍历策略：matrix[i][j] 表示以 [i][j] 为右下角的全 1 矩形：
//      高度：[i][j] 往上，连续 1 的个数都可能为高（高不同时，全 1 矩阵的最大宽可能不同，所以可以看出这里需要求 max）
//      宽度：假设上面确定的高为 h(0<h<=i+1) 时，对应的坐标为 [k][j]，k=i-h+1
//           此时，符合条件的矩阵的最大宽度应为 min( left[i][j], left[i-1][j], ..., left[k][j] )
//           其中，left[k][j] 表示点 [k][j] 往左，连续 1 的个数
// 所以，我们首先遍历整个数组，确定 left[m][n] 数组的值。然后再遍历每个点，按照上述思路，寻找最大全 1 矩阵的面积；
//
// 再回顾整个思路，我们其实也相当于是将二维数组按列拆分为了 n 个一维柱状图，横轴对应着矩阵的行，元素值对应 left。
// 这样，每一个柱状图求最大矩阵都对应着 T84 的柱状图里求最大矩阵。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

class P85_MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new P85_MaximalRectangle().new Solution();
        // TO TEST
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        char[][] matrix = {{'1'}};
        int answer = solution.maximalRectangle(matrix);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int res = 0;

            // 求 left 数组
            int[][] left = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[i][j] = (j == 0 ? 1 : left[i][j-1] + 1);
                    }
                }
            }

            // 按列将矩阵切分为柱状图
            for (int j = 0; j < n; j++) {
                int[] up = new int[m];
                int[] down = new int[m];
                Arrays.fill(down, m);

                Stack<Integer> mono_stack = new Stack<Integer>();
                for (int k = 0; k < m; k++) {
                    while (!mono_stack.isEmpty() && left[mono_stack.peek()][j] >= left[k][j]) {
                        down[mono_stack.peek()] = k;
                        mono_stack.pop();
                    }
                    up[k] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
                    mono_stack.push(k);
                }

                for (int c = 0; c < m; c++) {
                    res = Math.max(res, (down[c] - up[c] -1) * left[c][j]);
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(mn)，计算 left 矩阵需要 O(mn) 的时间；对每一列应用柱状图算法需要 O(m) 的时间，一共需要 O(mn) 的时间。
// 空间复杂度：O(mn)，分配了一个与给定矩阵等大的数组，用于存储每个元素的左边连续 1 的数量。