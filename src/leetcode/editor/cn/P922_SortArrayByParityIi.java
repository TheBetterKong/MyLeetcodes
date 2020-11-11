/******************************* Java：按奇偶排序数组 II *******************************/
// 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
// 输出：[4,5,2,7]
// 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 132 👎 0


/******************************* 题目思路 *******************************/
// 只要保证所有的偶数在正确的位置，那么所有的奇数也就在了正确的位置。
// 所以，这里以偶数作为基准，遍历 0 2 4....，不断在奇数位置找偶数，并放入偶数位置

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P922_SortArrayByParityIi {
    public static void main(String[] args) {
        Solution solution = new P922_SortArrayByParityIi().new Solution();
        // TO TEST
        int[] A = {4,2,5,7};
        int[] answer = solution.sortArrayByParityII(A);
        System.out.println(Arrays.toString(A));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int odd = 1; // 初始奇数的位置

            // 寻找出现在偶数位置的奇数，来交换
            for (int even = 0; even < A.length; even += 2) {
                if (A[even] % 2 != 0) {
                    // 开始查找第一个出现在奇数位置的偶数
                    while (A[odd] % 2 == 1) {
                        odd += 2;
                    }
                    // 找到了符合条件的偶数，开始交换
                    int tmp = A[even];
                    A[even] = A[odd];
                    A[odd] = tmp;
                    odd += 2;
                }
            }

            return A;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，注意，这里虽然是两层嵌套循环，但实际循环次数不会超过 n 次
// 空间复杂度 O(1)