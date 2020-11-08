/******************************* Java：阶乘后的零 *******************************/
// 给定一个整数 n，返回 n! 结果尾数中零的数量。
//
// 示例 1: 
//
// 输入: 3
// 输出: 0
// 解释:3! = 6, 尾数中没有零。
//
// 示例 2: 
//
// 输入: 5
// 输出: 1
// 解释:5! = 120, 尾数中有 1 个零.
//
// 说明: 你算法的时间复杂度应为 O(log n) 。 
// Related Topics 数学 
// 👍 379 👎 0


/******************************* 题目思路 *******************************/
// 最暴力的解法就是直接计算出阶乘后的值，然后，观察能被 10 整除的次数
//      但是阶乘的效率十分低，这里不那么可取。
// 在来观察一下，阶乘末尾有 0 的情况是 2*5，因此，我们的问题就转换为求解 n! 里有多少对 2*5 的情况。
//      同时观察能拆分出多少个 2 和 5，比较麻烦，对于任意的 n 拆出来的 2 的个数一定多于 5 的个数。所以直接看能拆出来多少个 5 即可；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P172_FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new P172_FactorialTrailingZeroes().new Solution();
        // TO TEST
        int n = 200;
        int answer = solution.trailingZeroes(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trailingZeroes(int n) {
//          时间复杂度为 O(n)
//            int count = 0;
//            for (int i = 5; i <= n; i = i+5) {
//                int m = i;
//                while (m % 5 == 0 && m != 0) {
//                    count ++;
//                    m /= 5;
//                }
//            }
//            return count;
            int count = 0;
            while (n > 0) {
                n /= 5;
                count += n;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(long n)
// 空间复杂度 O(1)
