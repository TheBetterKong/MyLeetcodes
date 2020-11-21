/******************************* Java：换酒问题 *******************************/
// 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
//
// 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。 
//
// 请你计算 最多 能喝到多少瓶酒。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：numBottles = 9, numExchange = 3
// 输出：13
// 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
// 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
// 
//
// 示例 2： 
//
// 
//
// 输入：numBottles = 15, numExchange = 4
// 输出：19
// 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。
// 所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
// 
//
// 示例 3： 
//
// 输入：numBottles = 5, numExchange = 5
// 输出：6
// 
//
// 示例 4： 
//
// 输入：numBottles = 2, numExchange = 3
// 输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= numBottles <= 100 
// 2 <= numExchange <= 100 
// 
// Related Topics 贪心算法 
// 👍 33 👎 0


/******************************* 题目思路 *******************************/
// 每喝 numExchange 瓶酒，就去换一瓶，那么剩余的酒就是 numBottles-numExchange+1
// 再重复这个过程，直到不再能换酒了，把剩余的酒喝掉即可；
//
// 当然了，如果从数学的角度分析：
//      每换一次酒，“净损失” numExchange-1 个瓶子。
//      那么，换了 n 次酒后，下一次还能换酒的条件就是：numBottles - n * (numExchange - 1) >= numExchange
//      现在的问题是，这个过程到底可以持续多少轮，即满足下面公式的 n 的最大值：n <= (numBottles - numExchange) / (numExchange - 1)
//      求逆反条件，即：满足 n > (numBottles - numExchange) / (numExchange - 1) 的 n 的最小值
//      也即：n = (numBottles - numExchange) / (numExchange - 1) + 1
//      所以，最终能喝到的酒的总数为：
//          numBottles + (numBottles - numExchange) / (numExchange - 1) + 1，（前提条件是能换）
// 所以，可以直接套用公式，一步出结果

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1518_WaterBottles {
    public static void main(String[] args) {
        Solution solution = new P1518_WaterBottles().new Solution();
        // TO TEST
        int numBottles = 22, numExchange = 6;
        int answer = solution.numWaterBottles(numBottles, numExchange);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numWaterBottles(int numBottles, int numExchange) {
            int bottles = numBottles;   // 现在有的酒数
            int sum = 0;       // 喝掉的酒数

            while (bottles >= numExchange) {
                sum += numExchange;
                bottles = bottles - numExchange + 1;
            }
            sum += bottles;
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，n = (numBottles - numExchange) / (numExchange - 1) + 1，原因见分析过程的数学方法
// 空间复杂度 O(1)
