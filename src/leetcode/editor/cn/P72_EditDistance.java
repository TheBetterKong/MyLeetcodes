/******************************* Java：编辑距离 *******************************/
// 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 示例 1： 
//
// 输入：word1 = "horse", word2 = "ros"
// 输出：3
// 解释：
// horse -> rorse (将 'h' 替换为 'r')
// rorse -> rose (删除 'r')
// rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 输入：word1 = "intention", word2 = "execution"
// 输出：5
// 解释：
// intention -> inention (删除 't')
// inention -> enention (将 'i' 替换为 'e')
// enention -> exention (将 'n' 替换为 'x')
// exention -> exection (将 'n' 替换为 'c')
// exection -> execution (插入 'u')
// 
//
//
// 提示： 
//
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1605 👎 0


/******************************* 题目思路 *******************************/
// 动态规划最核心的就是找到，什么是需要保存的中间变量（状态转移）。
// 这里是两个字符串，那么必定是一个二维数组：
// 假定 dis[i][j] 表示 word1 的前 i 个字母，到 word2 的前 j 个字母的编辑距离
//     （1）初始时 dis[0][0] = 0，dis[0][k] = dis[k][0] = k
//     （2）接下里就是如何确定 dis[i][j] 的值：
//              我们应该分析的是它与 dis[i-1][j]、dis[i][j-1]、dis[i-1][j-1] 有什么关系，即确定状态转移关系；
//                 ① dis[i][j-1] 为 word1 的前 i 个字符和 word2 的前 j - 1 个字符编辑距离的子问题：
//                          对于 word2 的第 j 个字符，我们在 word1 的末尾添加了一个相同的字符，那么 dis[i][j] 最小可以为 dis[i][j-1] + 1；
//                 ② dis[i-1][j] 为 word1 的前 i - 1 个字符和 world2 的前 j 个字符编辑距离的子问题：
//                          对于 word1 的第 i 个字符，我们在 word2 的末尾添加了一个相同的字符，那么 dis[i][j] 最小可以为 dis[i-1][j] + 1；
//                 ③ dis[i-1][j-1] 为 word1 前 i - 1 个字符和 word2 的前 j - 1 个字符编辑距离的子问题：
//                          对于 word2 的第 j 个字符，我们修改 word1 的第 i 个字符使它们相同，那么 dis[i][j] 最小可以为 dis[i-1][j-1] + 1。
//                          特别地，如果 word1 的第 i 个字符和 word2 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，dis[i][j] 最小可以为 dis[i-1][j-1]。
//              总结起来，状态转移方程就是：
//                      若 A 和 B 的最后一个字母相同：dis[i][j] = min( dis[i][j-1] + 1, dis[i-1][j] + 1, dis[i-1][j-1] ) = 1 + min( dis[i][j-1], dis[i-1][j], dis[i-1][j-1] - 1 )
//                      若 A 和 B 的最后一个字母不同：dis[i][j] = 1 + min( dis[i][j−1], dis[i−1][j], dis[i−1][j−1] )


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P72_EditDistance {
    public static void main(String[] args) {
        Solution solution = new P72_EditDistance().new Solution();
        // TO TEST
        String word1 = "intention", word2 = "execution";
        int answer = solution.minDistance(word1, word2);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            int lenth1 = word1.length(), lenth2 = word2.length();

            if (lenth1 * lenth2 == 0) return lenth1 + lenth2;

            // 声明并初始化 dis 数组
            int[][] dis = new int[lenth1 + 1][lenth2 + 1];
            for (int i = 0; i < lenth1 + 1; i++) {
                dis[i][0] = i;
            }
            for (int i = 0; i < lenth2 + 1; i++) {
                dis[0][i] = i;
            }

            // 遍历填写 dis 数组
            for (int row = 1; row < lenth1 + 1; row++) {
                for (int col = 1; col < lenth2 + 1; col++) {
                    if (word1.charAt(row - 1) == word2.charAt(col - 1)) {   // 最后一个字符相同
                        dis[row][col] = 1 + Math.min( dis[row][col - 1], Math.min( dis[row - 1][col], dis[row - 1][col - 1] - 1 ) );
                    } else {                                                // 最后一个字符不同
                        dis[row][col] = 1 + Math.min( dis[row][col - 1], Math.min( dis[row - 1][col], dis[row - 1][col - 1] ) );
                    }
                }
            }
            return dis[lenth1][lenth2];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 ：O(mn)，其中 m 为 word1 的长度，n 为 word2 的长度。
// 空间复杂度 ：O(mn)，需要大小为 O(mn) 的 DD 数组来记录状态值。