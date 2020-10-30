/******************************* Java：第三大的数 *******************************/
// 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
//
// 示例 1: 
//
// 
// 输入: [3, 2, 1]
//
// 输出: 1
//
// 解释: 第三大的数是 1.
// 
//
// 示例 2: 
//
// 
// 输入: [1, 2]
//
// 输出: 2
//
// 解释: 第三大的数不存在, 所以返回最大的数 2 .
// 
//
// 示例 3: 
//
// 
// 输入: [2, 2, 3, 1]
//
// 输出: 1
//
// 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
// 存在两个值为2的数，它们都排第二。
// 
// Related Topics 数组 
// 👍 169 👎 0


/******************************* 题目思路 *******************************/
// 这是典型 TOP-K 问题，可以利用 BFPRT 算法求解。但是这里，有点大材小用。
// 记得我们求解最大或者最小数时，直接维护一个 max 或者 min 变量，遍历时遇到更小或者更大就更新这个值，那遍历结束时，max min 就是最大 最小值
// 那么这里也可以采用类似的思路，第三大数，我们就只利用三个变量 one、two、three 来维护即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P414_ThirdMaximumNumber {
    public static void main(String[] args) {
        Solution solution = new P414_ThirdMaximumNumber().new Solution();
        // TO TEST
        int []nums = {2, 2, 3, 1};
        int answer = solution.thirdMax(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int thirdMax(int[] nums) {
            if(nums == null || nums.length == 0) throw new IllegalArgumentException("nums is null or nums.length is 0");
            int one = nums[0];
            long two = Long.MIN_VALUE;
            long three = Long.MIN_VALUE;

            for(int i = 1; i < nums.length; i++){
                if(nums[i] == one || nums[i] == two || nums[i] == three) continue;
                if(nums[i] > one){
                    three = two;
                    two = one;
                    one = nums[i];
                }
                else if(nums[i] > two){
                    three = two;
                    two = nums[i];
                }
                else if(nums[i] > three){
                    three = nums[i];
                }
            }
            if(three == Long.MIN_VALUE) return one;
            return (int)three;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)