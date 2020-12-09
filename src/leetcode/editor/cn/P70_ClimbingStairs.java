/******************************* Java：爬楼梯 *******************************/
// 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
// 输出： 2
// 解释： 有两种方法可以爬到楼顶。
// 1.  1 阶 + 1 阶
// 2.  2 阶
//
// 示例 2： 
//
// 输入： 3
// 输出： 3
// 解释： 有三种方法可以爬到楼顶。
// 1.  1 阶 + 1 阶 + 1 阶
// 2.  1 阶 + 2 阶
// 3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1366 👎 0


/******************************* 题目思路 *******************************/
// 本题可以直接找递推关系，利用递推来求解：
//      要想知道跳到 n 级台阶多少种跳法，那么包含两种情况：
//          ① 从 n-1 级台阶跳一级到 n 级；
//          ② 从 n-2 级台阶跳 2 级到 n 级台阶。
//      这样，递归关系就出来了：
//          f(n) = f(n-1) + f(n-2)
// 这就是 “斐波拉契数列” 的求解问题；
// 但是这里，需要重点讲解的是 “斐波拉契数列” 的快速算法：
//      方法一：矩阵快速幂
//          f(n-1) + f(n) = f(n+1) 可以用矩阵转化为：
//          [ [1, 1], [1, 0] ] * [ f(n), f(n-1) ] = [ f(n+1), f(n) ] 递推下去：
//          [ [1, 1], [1, 0] ]^n * [ f(1), f(0) ] = [ f(n+1), f(n) ] 现在问题就成了求 [ [1, 1], [1, 0] ]^n
//          而求 a^n 可以转化为 （a^2) ^ (n/2)，这样可以将 n 次运算转换为 logn 次运算（快速幂）
//      方法二：求通项
//          递推关系 f(n-1) + f(n) = f(n+1) 可以得出特征方程为：x^2 = x + 1
//          求得 x1 = (1 + sqrt(5)) / 2，x2 = (1 - sqrt(5)) / 2，于是通解就为 f(n) = c1 * x1^2 + c2 * x2^2，再将 f(0)，f(1) 带入
//          就可以直接拿到 f(n) 的求解式

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P70_ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new P70_ClimbingStairs().new Solution();
        // TO TEST
        int n = 4;
        int answer = solution.climbStairs(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            int[][] p = {{1, 1}, {1, 0}};
            int[][] res = pow(p, n);
            return res[0][0];
        }

        // 求矩阵的 n 次幂
        private int[][] pow(int[][] a, int n) {
            int[][] ans = {{1, 0}, {0, 1}};
            while (n > 0) {
                if ((n & 1) == 1) {
                    ans = multiply(ans, a);
                }
                n >>= 1;
                a = multiply(a, a);
            }
            return ans;
        }

        // 两个矩阵相乘
        private int[][] multiply(int[][] a, int[][] b) {
            int[][] c = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
                }
            }
            return c;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O（logn）
// 空间复杂度 O（1)
