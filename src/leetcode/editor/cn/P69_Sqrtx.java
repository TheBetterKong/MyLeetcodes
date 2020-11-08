/******************************* Java：x 的平方根 *******************************/
// 实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
// 输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
// 输出: 2
// 说明: 8 的平方根是 2.82842...,
//   由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 535 👎 0


/******************************* 题目思路 *******************************/
// 就是查找 0-x 的整数 ans，使得 ans 是满足 ans^2 <= x 的最大整数。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P69_Sqrtx {
    public static void main(String[] args) {
        Solution solution = new P69_Sqrtx().new Solution();
        // TO TEST
        int x = 8;
        int answer = solution.mySqrt(x);
        System.out.print(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            int left = 0, right = x, ans = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if ((long) mid * mid <= x) { // 防止溢出
                    ans = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(logx)，即为二分查找需要的次数。
// 空间复杂度：O(1)O
