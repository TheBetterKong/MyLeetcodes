/******************************* Java：计数质数 *******************************/
// 统计所有小于非负整数 n 的质数的数量。
//
// 
//
// 示例 1： 
//
// 输入：n = 10
// 输出：4
// 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 输入：n = 0
// 输出：0
// 
//
// 示例 3： 
//
// 输入：n = 1
// 输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 106 
// 
// Related Topics 哈希表 数学 
// 👍 471 👎 0


/******************************* 题目思路 *******************************/
// 所谓 ”厄拉多塞筛法“ 就是从 2 开始在整个 n 的域内，筛去相应数的倍数：
//   i = 2 时，去除所有 2 的倍数的数；
//   i = 3 时，去除所有 3 的倍数的数；
//      ...
//   i = n-1 时，去除所有 n-1 的倍数的数；
// 采用 n bit 的二进制数来做标记：（在较小 n 的情况下适用，对于大于 64 的数会产生溢出，但是较小数的内存优化又不那么明显，这里仅仅给出一个思考方向）
//      一个二进制的某位为 1，表示这个数被去除;
//      例如：000101，代表去除数字 1 和 3
// 所以还是老老实实，采用 n 项的 boolean 型数组来存储；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P204_CountPrimes {
    public static void main(String[] args) {
        Solution solution = new P204_CountPrimes().new Solution();
        // TO TEST
        int n = 10;
        int answer = solution.countPrimes(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countPrimes(int n) {
            int count = 0;
            boolean[] flag = new boolean[n]; // java 初始默认为 false

            for (int i = 2; i < n; i++) {
                if (!flag[i-1]) {
                    count ++;
                    for (int k = 2; k * i <= n; k++) {
                        flag[k * i - 1] = true;
                    }
                }
            }

            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n^2)
// 空间复杂度 O(n)，需要 n 大小的 boolean 数组来存储标记位
