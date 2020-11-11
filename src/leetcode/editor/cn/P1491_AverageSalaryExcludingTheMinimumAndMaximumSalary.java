/******************************* Java：去掉最低工资和最高工资后的工资平均值 *******************************/
// 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
//
// 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。 
//
// 
//
// 示例 1： 
//
// 输入：salary = [4000,3000,1000,2000]
// 输出：2500.00000
// 解释：最低工资和最高工资分别是 1000 和 4000 。
// 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
// 
//
// 示例 2： 
//
// 输入：salary = [1000,2000,3000]
// 输出：2000.00000
// 解释：最低工资和最高工资分别是 1000 和 3000 。
// 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
// 
//
// 示例 3： 
//
// 输入：salary = [6000,5000,4000,3000,2000,1000]
// 输出：3500.00000
// 
//
// 示例 4： 
//
// 输入：salary = [8000,9000,2000,3000,6000,1000]
// 输出：4750.00000
// 
//
// 
//
// 提示： 
//
// 
// 3 <= salary.length <= 100 
// 10^3 <= salary[i] <= 10^6 
// salary[i] 是唯一的。 
// 与真实值误差在 10^-5 以内的结果都将视为正确答案。 
// 
// Related Topics 排序 数组 
// 👍 13 👎 0


/******************************* 题目思路 *******************************/
// 主要难点在于怎样找到数组里的最大值和最小值；
// 同时，求和操作必不可少，那么将这二者结合到一起。一次遍历即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1491_AverageSalaryExcludingTheMinimumAndMaximumSalary {
    public static void main(String[] args) {
        Solution solution = new P1491_AverageSalaryExcludingTheMinimumAndMaximumSalary().new Solution();
        // TO TEST
        int[] salary = {4000, 3000, 1000, 2000};
        double answer = solution.average(salary);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double average(int[] salary) {
            double sum = 0;
            double min = Integer.MAX_VALUE;
            double max = Integer.MIN_VALUE;

            // 求和，并找出数组的最大，最小值
            for (int n : salary) {
                sum += n;
                if (n < min) {
                    min = n;
                }
                if (n > max) {
                    max = n;
                }
            }

            return (sum - min - max) / (salary.length - 2);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)