/******************************* Java：盛最多水的容器 *******************************/
// 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
// ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
// 输出：49
// 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
//
//
// 示例 2： 
//
// 输入：height = [1,1]
// 输出：1
// 
//
// 示例 3： 
//
// 输入：height = [4,3,2,1,4]
// 输出：16
// 
//
// 示例 4： 
//
// 输入：height = [1,2,1]
// 输出：2
// 
//
// 提示： 
//
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 2150 👎 0


/******************************* 题目思路 *******************************/
// 分析题目，控制盛水面积的两个因素：
//      1. 两个边界的距离
//      2. 两个边界中较矮的那个
// 如果采用暴力破解，时间复杂度无疑会达到 n^2 级别，肯定不是最优的。那么，问题在于如果控制这两个因素的遍历
// “两个边界的距离” 那下意识就会想到 “双指针”：
//      首先将这个边距设到最大，然后循环遍历左右指针，移动较矮的那个来缩短间距；
//      然后，在所有的这些情况里选择，面积最大的即可；
// 这样处理的完备性，可以参见官方的证明，其实仔细想想也是好理解的；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P11_ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new P11_ContainerWithMostWater().new Solution();
        // TO TEST
        int[] height = {1,8,6,2,5,4,8,3,7};
        int answer = solution.maxArea(height);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int area = 0;

            if (left == right)
                return 0;
            while (right > left) {
                area = Math.max((right - left) * Math.min(height[left], height[right]), area);
                if (height[left] <= height[right])
                    left ++;
                else
                    right --;
            }
            return area;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，左右指针遍历至相遇，对数据进行了一次完整的遍历
// 空间复杂度：O(1)，并没有新的存储结构，只需要额外的常数级别的空间