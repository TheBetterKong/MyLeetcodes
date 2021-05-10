/******************************* Java：旋转数组 *******************************/
//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
// 输出: [5,6,7,1,2,3,4]
// 解释:
// 向右旋转 1 步: [7,1,2,3,4,5,6]
// 向右旋转 2 步: [6,7,1,2,3,4,5]
// 向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
// 输出: [3,99,-1,-100]
// 解释:
// 向右旋转 1 步: [99,-1,-100,3]
// 向右旋转 2 步: [3,99,-1,-100]
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 698 👎 0


/******************************* 题目思路 *******************************/
// 暴力解法：每次向后旋转一次，每次旋转需要移动 n 个元素，最终时间复杂度 O(n*k)
// 方法二：最容易想到的，就是开辟一个新数组转存，然后再恢复至原数组。但题目要求，空间复杂度为 O(1) 的原地算法，pass；
// 方法三：那就画个图，移位，找规律。
//      该方法可以转换到一个现实模型：同学换座位。
//      第 1 个同学离开座位去第 k+1 个座位，第 k+1 个座位的同学被挤出去了，去坐他后 k 个座位，如此反复。
//      但是会出现一种情况，就是其中一个同学被挤开之后，坐到了第一个同学的位置（空位置，没人被挤出来），但是此时还有人没有调换位置，这样就顺着让第二个同学换位置。
//      n 个同学，总共需要换 n 次座位，所以也需要用一个 count 来计数。
// 方法四：leetcode 上还介绍了一种：数组旋转反向的办法。
//      首先将所有元素反转。然后反转前 k 个元素，再反转后面 n−k 个元素，就能得到想要的结果。
//      假设 n=7 且 k=3
//      原始数组                  : 1 2 3 4 5 6 7
//      反转所有数字后             : 7 6 5 4 3 2 1
//      反转前 k 个数字后          : 5 6 7 4 3 2 1
//      反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P189_RotateArray {
    public static void main(String[] args) {
        Solution solution = new P189_RotateArray().new Solution();
        // TO TEST
        int []nums = {-1,-100,3,99};
        int k = 3;
        solution.rotate(nums,k);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            int count = 0;
            int startidex = 0;               // 每一轮旋转换位的开始处，第一轮从 0 开始，后面遇到断环但换位未结束的情况不断 +1
            // 一次循环就是一次环状换位，总共需要 n 次换位，算法结束
            while (count < nums.length){
                // 初始化
                int currentidex = startidex;     // 当前换位进行到哪
                int current = nums[startidex]; // 当前换位处的值
                // 开始换位
                do{
                    int nextidex = (currentidex + k) % nums.length;
                    int temp = nums[nextidex];
                    nums[nextidex] = current;
                    current = temp;
                    currentidex = nextidex;
                    count++;    // 换一次位 +1
                } while (currentidex != startidex);
                // 一次环状换位可能换位还未结束
                startidex++;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)。n 个同学 n 次换位。
// 空间复杂度：O(1)。