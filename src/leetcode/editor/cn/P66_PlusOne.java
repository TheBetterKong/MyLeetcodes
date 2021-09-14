/******************************* Java：加一 *******************************/
//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
// 输出: [1,2,4]
// 解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
// 输出: [4,3,2,2]
// 解释: 输入数组表示数字 4321。
// 
// Related Topics 数组 
// 👍 547 👎 0


/******************************* 题目思路 *******************************/
// 本题是简单的数学逻辑，只需要缕清思路即可。
//      无非就是从最低位开始，是 9 就变 0，高位再开始进行 +1 判断；非 9 就可以直接停止算法

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P66_PlusOne {
    public static void main(String[] args) {
        Solution solution = new P66_PlusOne().new Solution();
        // TO TEST
        int []digits = {9,9,9,9};
        int []answer = solution.plusOne(digits);
        System.out.println(Arrays.toString(answer));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)