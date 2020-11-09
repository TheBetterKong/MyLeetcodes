/******************************* Java：缺失数字 *******************************/
// 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
//
// 
//
// 示例 1: 
//
// 输入: [3,0,1]
// 输出: 2
// 
//
// 示例 2: 
//
// 输入: [9,6,4,2,3,5,7,0,1]
// 输出: 8
// 
//
// 
//
// 说明: 
// 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
// Related Topics 位运算 数组 数学 
// 👍 311 👎 0


/******************************* 题目思路 *******************************/
// 本题比较常见的思路就是先对数组进行排序，然后循环遍历，找出空缺数。时间复杂度 O(nlogn)
// 第二种方法：将数组转存至 hashset，然后遍历，查找缺失值。（这种方法不管时间还是空间复杂度都比较低）；
// 数学方法：0~n 的数，那我们就直接求出 0~n 的叠加和，然后挨个数字减，最终的值一定就是差的数；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P268_MissingNumber {
    public static void main(String[] args) {
        Solution solution = new P268_MissingNumber().new Solution();
        // TO TEST
        int []nums = {9,6,4,2,3,5,7,0,1};
        int answer = solution.missingNumber(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int missingNumber(int[] nums) {
            int sum = 0;
            for(int i = 1; i <= nums.length; i++){
                sum += i;
                sum -= nums[i-1];
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)