/******************************* Java：区域和检索 - 数组不可变 *******************************/
// 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
//
// 
// 
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 使用数组 nums 初始化对象 
// int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
// （也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
// 
//
// 
//
// 示例： 
//
// 
// 输入：
// ["NumArray", "sumRange", "sumRange", "sumRange"]
// [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
// 输出：
// [null, 1, -1, -3]
//
// 解释：
// NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
// numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
// numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
// numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 0 <= i <= j < nums.length 
// 最多调用 104 次 sumRange 方法 
// 
// 
// 
// Related Topics 动态规划 
// 👍 215 👎 0


/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P303_RangeSumQueryImmutable {
    public static void main(String[] args) {
        // Solution solution = new P303_RangeSumQueryImmutable().new Solution();
        // TO TEST
        NumArray solution = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
        int answer = solution.sumRange(0,3);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class NumArray {

        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j+1] - sum[i];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(i,j);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(1)，一次简单的查表取数即可
// 空间复杂度 O(n)