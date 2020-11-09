/******************************* Java：两数之和 II - 输入有序数组 *******************************/
//给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。 
//
// 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。 
//
// 说明: 
//
// 
// 返回的下标值（index1 和 index2）不是从零开始的。 
// 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 
// 
//
// 示例: 
//
// 输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。 
// Related Topics 数组 双指针 二分查找 
// 👍 400 👎 0


/******************************* 题目思路 *******************************/
// 本题对比 T1，更加简单，既然数组已经有序，那就是简单的双指针查找

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P167_TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new P167_TwoSumIiInputArrayIsSorted().new Solution();
        // TO TEST
        int []numbers = {2,7,11,15};
        int target = 9;
        int []answer = solution.twoSum(numbers,target);
        System.out.println(Arrays.toString(answer));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = numbers.length - 1;
        while (index1 < index2){
            if(numbers[index1] + numbers[index2] == target) return new int [] {index1+1,index2+1};
            if(numbers[index1] + numbers[index2] < target) index1++;
            if(numbers[index1] + numbers[index2] > target) index2--;
        }
        return new int [] {-1,-1};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......