/******************************* Java：买卖股票的最佳时机 *******************************/
// 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。 
//
// 注意：你不能在买入股票前卖出股票。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
// 输出: 5
// 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
// 输出: 0
// 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
// Related Topics 数组 动态规划 
// 👍 1205 👎 0


/******************************* 题目思路 *******************************/
// 数组中求最值的变型题，说白了，就是求数组中两个元素的最大差值
// 比较容易想到是直接双重循环，把数组所有满足要求的两数之差求出来，再求最值，时间复杂度 O(n^2)；
// 改进：
//      观察题目，理一下题意，以每一天为参照点，我们记录每一天与历史最低价的差值，所有天数结束后，最大的差值就是最大收益
//      转化为算法，就是遍历数组，不断的求最小值，然后再在这个最小值的基础上找最大差值。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new P121_BestTimeToBuyAndSellStock().new Solution();
        // TO TEST
        int []prices = {7,1,5,3,6,4};
        int ans = solution.maxProfit(prices);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices.length == 0) return 0;
            int min = prices[0];    // 当前的最小价格
            int maxget = 0;    // 当前的最大收益
            for(int i = 1; i < prices.length; i++) {
                if (prices[i] < min) {
                    min = prices[i];
                }
                if(prices[i] - min > maxget){
                    maxget = prices[i] - min;
                }
            }
            return maxget;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)