/******************************* Java：玩筹码 *******************************/
// 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
//
// 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）： 
//
// 
// 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。 
// 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。 
// 
//
// 最开始的时候，同一位置上也可能放着两个或者更多的筹码。 
//
// 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。 
//
// 
//
// 示例 1： 
//
// 输入：chips = [1,2,3]
// 输出：1
// 解释：第二个筹码移动到位置三的代价是 1，第一个筹码移动到位置三的代价是 0，总代价为 1。
// 
//
// 示例 2： 
//
// 输入：chips = [2,2,2,3,3]
// 输出：2
// 解释：第四和第五个筹码移动到位置二的代价都是 1，所以最小总代价为 2。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= chips.length <= 100 
// 1 <= chips[i] <= 10^9 
// 
// Related Topics 贪心算法 数组 数学 
// 👍 66 👎 0


/******************************* 题目思路 *******************************/
// 由题意：
//      将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
//
// 那么：
//      （1）如果筹码位置为奇数，可以通过多次无代价的移动将其变为位置 1；
//      （2）如果筹码位置为偶数，可以通过多次无代价的移动将其变为位置 0；
//
// 这样处理处理之后，最终砝码都在 0 和 1 这两个位置；
//
// 这时候：
//      如果 0 比较多，就把所有的 1 移动到 0 处；
//      如果 1 比较多，就把所有的 0 移动到 1 处；
//
// 因此，返回 0 和 1 中出现次数较少的那个值对应的次数即可。
//
// 那么题目也就可以转换为：
//      数组中，奇数偶数出现次数的较小者。


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1217_MinimumCostToMoveChipsToTheSamePosition {
    public static void main(String[] args) {
        Solution solution = new P1217_MinimumCostToMoveChipsToTheSamePosition().new Solution();
        // TO TEST
        int[] chips = {2,2,2,3,3};
        int answer = solution.minCostToMoveChips(chips);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostToMoveChips(int[] position) {
            int sg = 0, db = 0;
            for (int n : position) {
                if (n % 2 == 0) {
                    db ++;
                }
                else {
                    sg ++;
                }
            }

            return sg > db ? db : sg;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)
