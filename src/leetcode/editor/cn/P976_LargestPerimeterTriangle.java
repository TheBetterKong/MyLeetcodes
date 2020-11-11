/******************************* Java：三角形的最大周长 *******************************/
// 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
//
// 如果不能形成任何面积不为零的三角形，返回 0。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：[2,1,2]
// 输出：5
// 
//
// 示例 2： 
//
// 输入：[1,2,1]
// 输出：0
// 
//
// 示例 3： 
//
// 输入：[3,2,3,4]
// 输出：10
// 
//
// 示例 4： 
//
// 输入：[3,6,2,3]
// 输出：8
// 
//
// 
//
// 提示： 
//
// 
// 3 <= A.length <= 10000 
// 1 <= A[i] <= 10^6 
// 
// Related Topics 排序 数学 
// 👍 88 👎 0


/******************************* 题目思路 *******************************/
// 要满足周长最大，无非就是选择最长的（能构成三角形的）三边：
//    那么要判断是否任意两边长大于第三边，为了便利，先对数组排序.
//    然后，直接查找满足 A[i] + A[i+1] > A[i+2]，的最大 i 即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P976_LargestPerimeterTriangle {
    public static void main(String[] args) {
        Solution solution = new P976_LargestPerimeterTriangle().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestPerimeter(int[] A) {
            Arrays.sort(A);
            // 倒数第 3 个元素开始，比较它后面的两个元素是否能与它构成三角形
            for (int i = A.length - 3; i >= 0; --i)
                if (A[i] + A[i+1] > A[i+2])
                    return A[i] + A[i+1] + A[i+2];
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(nlogn)，主要来源于数组排序
// 空间复杂度 O(1)