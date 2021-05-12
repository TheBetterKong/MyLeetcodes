/******************************* Java：接雨水 *******************************/
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1：
// 
// 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
// 输出：6
// 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
// 
//
// 示例 2： 
//
// 输入：height = [4,2,0,3,2,5]
// 输出：9
// 
//
// 
//
// 提示： 
//
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 
// 👍 2337 👎 0


/******************************* 题目思路 *******************************/
// 这道题有两种分析思路：
// （1） 层次分析：自底向上分析能接到得水量
//      第 1 层：从数组里第一个高度 >=1 的数开始，到最后一个高度 >=1 的数结束，这期间高度 <1 的数的个数；
//      第 2 层：从数组里第一个高度 >=2 的数开始，到最后一个高度 >=2 的数结束，这期间高度 <2 的数的个数；
//      ......
//      第 i 层：从数组里第一个高度 >=i 的数开始，到最后一个高度 >=i 的数结束，这期间高度 <i 的数的个数；
//      ......
//      直到第 max(nums) 层
//   此方法的时间复杂度为：O(kn)，k 为数组中最大的元素值，n 为数组元素个数。但是，如果数组里最大值 max 很大时，算法耗时很严重。
//   （题目说明里，0 <= height[i] <= 105，所以 k 相对来说能接受，但是 leetcode 的测试用例里 height[i] 却出现了很大的情况，
//      导致我测试未通过）
//
//
// （2） 横向分析：从左至右遍历每个元素，分析能接到的水量
//      对于每个元素 i，在该位置它能接到的水的最大高度应该为 i 两边最大高度的较小值；所以现在的问题在于怎样找“左右两边的最大值”；
//
//      最直观的方式是维护两个数组：leftmax 和 rightmax 分别保存元素 i 左（右）边的最大值，这两个数组可以用动态规划思想求解；
//          此时，时间和空间复杂度都为 O(n)；
//
//      为了进一步优化空间开销，考虑省略这两个数组，采用双指针的思想实现：
//          变量设置：维护左右两个指针 left 和 right，以及两个变量 leftmax 和 rightmax 用来记录已经遍历过的元素中，左边的最大值和右边的最大值
//          过程：
//              左右指针移动，并且根据 height[left] 和 height[right] 更新 leftmax 和 rightmax 的值
//              某个时刻：
//              ① 如果 leftmax < rightmax，则说明位置 left 处，左边的最大高度一定比右边的最大高度小，
//                 那么位置 left 处左右两边最大高度的较小者就为 leftmax，该处的积水量就是 leftmax-height[left]，更新 left 指针；
//              ② 如果 rightmax < leftmax，则说明位置 right 处，右边的最大高度一定比左边的最大高度小，
//                 那么位置 right 处左右两边最大高度的较小者就为 rightmax，该处的积水量就是 rightmax-height[right]，更新 right 指针；
//      该方法时间复杂度为 o(n)，空间复杂度为 O(1)

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P42_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P42_TrappingRainWater().new Solution();
        // TO TEST
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int answer = solution.trap(height);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            // return trapHiera(height);
            return trapHori(height);
        }


        // 方法一：层次分析，求解接水量
        private int trapHiera(int[] height) {
            if (height.length == 0 || height.length == 1) return 0;

            int max = Arrays.stream(height).max().getAsInt();
            int sumwater = 0;
            for (int i = 1; i <= max; i ++) {
                sumwater += getnums(height, i);
            }
            return sumwater;
        }

        private int getnums(int[] height, int val) {
            if (height.length == 0 || height.length == 1) return 0;

            int nums = 0;
            int lenth = height.length;
            int left = 0, right = lenth - 1;    // 左右遍历指针
            boolean tagl = false, tagr = false;        // 标记是否找到左右端点

            // 双指针，查找区间：“第一个高度 >=2 的数开始，到最后一个高度 >=2 的数”
            while (left <= right) {
                if (!tagl) {
                    if (height[left] >= val)
                        tagl = true;
                    left++;
                }
                if (!tagr) {
                    if (height[right] >= val)
                        tagr = true;
                    right--;
                }
                if (tagl & tagr) {
                    if (height[left] < val && left <= right)
                        nums++;
                    left++;
                    if (height[right] < val && left <= right)
                        nums++;
                    right--;
                }
            }
            return nums;
        }

        // 方法二：横向分析求解接水量
        private int trapHori(int[] height) {
            if (height.length == 0 || height.length == 1) return 0;

            int lenth = height.length;
            int left = 0, right = lenth - 1;    // 左右遍历指针
            int leftmax = 0, rightmax = 0;
            int sumwater = 0;

            while (left < right) {
                leftmax = Math.max(leftmax, height[left]);
                rightmax = Math.max(rightmax, height[right]);
                if (leftmax < rightmax) {
                    sumwater += leftmax - height[left];
                    left++;
                } else {
                    sumwater += rightmax - height[right];
                    right--;
                }
            }
            return sumwater;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 见题解部分