/******************************* Java：单词搜索 *******************************/
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
//
// 示例 1： 
//
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// 输出：true
// 
//
// 示例 2： 
//
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
// 输出：true
// 
//
// 示例 3： 
//
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
// 输出：false
// 
//
// 提示： 
//
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯算法 
// 👍 904 👎 0


/******************************* 题目思路 *******************************/
// 也是一道典型的回溯法题目，有两个难点：
//      ① 可以从任意地方开始
//      ② 可以访问任意相邻的元素
//
// 构建 check(i, j, k) 函数表示：从 board 的 [i,j] 位置能否查到 word 从 k 开始的子字符串：
//      如果 board[i,k] ≠ word[k]，当前不匹配，return false
//      如果 k = word.length，则说明完成了匹配 return true
//      否则，继续从相邻的位置出发，看能否遍历到 word[k+1]
//
// 因为题目规定不能重复，所以还需要一个数组 visited 记录 board 里的元素是否已经访问过；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P79_WordSearch {
    public static void main(String[] args) {
        Solution solution = new P79_WordSearch().new Solution();
        // TO TEST
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        boolean answer = solution.exist(board, word);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            int row = board.length, col = board[0].length;
            int wlenth = word.length();
            if (row * col < wlenth)
                return false;
            int[][] visited = new int[row][col];    // 初始化，都为 0 表示未被访问

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    boolean flag = check(board, i, j, word, 0, visited);
                    if (flag) { // 期间只要有一个地方能遍历出 word 就为 true
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean check(char[][] board, int i, int j, String word, int k, int[][] visited) {
            // 递归出口：符合和不符合两种情况
            if (board[i][j] != word.charAt(k)) {
                return false;
            } else if (k == word.length() - 1) {
                return true;
            }
            visited[i][j] = 1;
            // 四个方向，递归往下进行:上、下、左、右
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dire : directions) {
                int newi = i + dire[0], newj= j + dire[1];
                if ( (newi >= 0 && newi < board.length) &&
                        (newj >= 0 && newj < board[0].length) &&
                        (visited[newi][newj] == 0)) {
                    boolean flag = check(board, newi, newj, word, k + 1, visited);
                    if (flag) {
                        return true;
                    }
                }
            }
            visited[i][j] = 0; // 当前位置没法找到正解，恢复该位置的 visited 供当前路径递归往下
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：一个非常宽松的上界为 O(MN ⋅ 3^L)，其中 M,N 为网格的长度与宽度，L 为字符串 word 的长度。
//          在每次调用函数 check 时，除了第一次可以进入 4 个分支以外，其余时间我们最多会进入 3 个分支
//          （因为每个位置只能使用一次，所以走过来的分支没法走回去）。
//          由于单词长为 L，故 check(i,j,0) 的时间复杂度为 O(3 L)，而我们要执行 O(MN) 次检查。
//          然而，由于剪枝的存在，我们在遇到不匹配或已访问的字符时会提前退出，终止递归流程。
//          因此，实际的时间复杂度会远远小于 Θ(MN⋅3^L)。
//
// 空间复杂度：O(MN)。额外开辟了 O(MN) 的 visited 数组，同时栈的深度最大为 O(min(L,MN))。